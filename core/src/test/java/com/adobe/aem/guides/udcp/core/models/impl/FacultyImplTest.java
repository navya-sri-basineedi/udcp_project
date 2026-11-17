package com.adobe.aem.guides.udcp.core.models.impl;

import com.adobe.aem.guides.udcp.core.models.Faculty;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AemContextExtension.class)
class FacultyImplTest {

    @Test
    void adaptsCompositeFacultyMembersInStoredOrder(AemContext context) {
        context.addModelsForClasses(FacultyImpl.class);
        Resource facultyResource = context.create().resource(
                "/content/faculty_profile",
                "facultyName", "Legacy Faculty",
                "sling:resourceType", "/apps/udcp/components/udcp-components/faculty-profile");
        context.create().resource(
                "/content/faculty_profile/facultyMembers/item0",
                "facultyName", "Ada Lovelace",
                "designation", "Professor",
                "school", "Engineering",
                "facultyPhoto", "/content/dam/ada.png",
                "qualifications", "Ph.D.",
                "bio", "Faculty bio");
        context.create().resource(
                "/content/faculty_profile/facultyMembers/item1",
                "facultyName", "Grace Hopper",
                "school", "Management");

        Faculty faculty = facultyResource.adaptTo(Faculty.class);

        assertNotNull(faculty);
        assertTrue(faculty.isComposite());
        assertEquals(2, faculty.getFacultyMembers().size());
        assertEquals("Ada Lovelace", faculty.getFacultyMembers().get(0).getFacultyName());
        assertEquals("Professor", faculty.getFacultyMembers().get(0).getDesignation());
        assertEquals("Engineering", faculty.getFacultyMembers().get(0).getSchool());
        assertEquals("/content/dam/ada.png", faculty.getFacultyMembers().get(0).getFacultyPhoto());
        assertEquals("Ph.D.", faculty.getFacultyMembers().get(0).getQualifications());
        assertEquals("Faculty bio", faculty.getFacultyMembers().get(0).getBio());
        assertEquals("Grace Hopper", faculty.getFacultyMembers().get(1).getFacultyName());
        assertEquals("Management", faculty.getFacultyMembers().get(1).getSchool());
        assertNull(faculty.getFacultyMembers().get(1).getFacultyPhoto());
        assertNull(faculty.getFacultyMembers().get(1).getBio());
    }

    @Test
    void keepsLegacyFacultyPropertiesWhenNoCompositeMembersExist(AemContext context) {
        context.addModelsForClasses(FacultyImpl.class);
        Resource facultyResource = context.create().resource(
                "/content/faculty_profile",
                "facultyName", "Legacy Faculty",
                "designation", "Professor",
                "sling:resourceType", "/apps/udcp/components/udcp-components/faculty-profile");

        Faculty faculty = facultyResource.adaptTo(Faculty.class);

        assertNotNull(faculty);
        assertFalse(faculty.isComposite());
        assertTrue(faculty.getFacultyMembers().isEmpty());
        assertEquals("Legacy Faculty", faculty.getFacultyName());
        assertEquals("Professor", faculty.getDesignation());
    }
}
