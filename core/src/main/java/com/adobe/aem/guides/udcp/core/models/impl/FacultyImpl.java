package com.adobe.aem.guides.udcp.core.models.impl;
 
import com.adobe.aem.guides.udcp.core.models.Faculty;
 
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
 
@Model(
    adaptables = Resource.class,
    adapters = Faculty.class,
    resourceType = "/apps/udcp/components/udcp-components/faculty-profile",
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class FacultyImpl implements Faculty {
 
    @ValueMapValue
    private String facultyName;
 
    @ValueMapValue
    private String designation;
 
    @ValueMapValue
    private String school;
 
    @ValueMapValue
    private String facultyPhoto;
 
    @ValueMapValue
    private String qualifications;
 
    @ValueMapValue
    private String bio;
 
    @Override
    public String getFacultyName() {
        return facultyName;
    }
 
    @Override
    public String getDesignation() {
        return designation;
    }
 
    @Override
    public String getSchool() {
        return school;
    }
 
    @Override
    public String getFacultyPhoto() {
        return facultyPhoto;
    }
 
    @Override
    public String getQualifications() {
        return qualifications;
    }
 
    @Override
    public String getBio() {
        return bio;
    }
}
 