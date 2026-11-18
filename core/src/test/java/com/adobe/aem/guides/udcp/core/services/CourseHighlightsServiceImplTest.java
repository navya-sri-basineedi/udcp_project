package com.adobe.aem.guides.udcp.core.services;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AemContextExtension.class)
class CourseHighlightsServiceImplTest {

    @Test
    void testConfiguredCourseHighlights(AemContext context) {
        CourseHighlightsServiceImpl service = new CourseHighlightsServiceImpl();
        context.registerInjectActivateService(
            service,
            "sectionHeading", "Configured Heading",
            "sectionDescription", "Configured Description"
        );

        assertEquals("Configured Heading", service.getSectionHeading());
        assertEquals("Configured Description", service.getSectionDescription());
    }

    @Test
    void testDefaultCourseHighlights(AemContext context) {
        CourseHighlightsServiceImpl service = new CourseHighlightsServiceImpl();
        context.registerInjectActivateService(service);

        assertEquals("Explore Our Courses", service.getSectionHeading());
        assertEquals(
            "Discover a wide range of industry-focused programs designed to build your skills, encourage innovation, and prepare you for successful careers.",
            service.getSectionDescription()
        );
    }
}
