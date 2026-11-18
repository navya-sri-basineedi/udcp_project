
package com.adobe.aem.guides.udcp.core.models.impl;
 
import java.util.Collections;
import java.util.List;

import com.adobe.aem.guides.udcp.core.models.Faculty;
 
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
 
@Model(
    adaptables = Resource.class,
    adapters = Faculty.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

/**
 * Sling Model implementation for the Faculty Component.
 * Retrieves faculty profile information from Aem resources.
 * 
 * @author
 */
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

    @ChildResource(name = "facultyMembers")
    private List<Faculty> facultyMembers;
 
   /**   (non-Javadoc)
    * Returns the faculty members name.
    * 
    * @return faculty name
    * @see com.adobe.aem.guides.udcp.core.models.Faculty#getFacultyName()
    */
    @Override
    public String getFacultyName() {
        return facultyName;
    }
 
    /**    (non-Javadoc)
     * Returns the faculty members designation.
     * 
     * @return faculty designation
     * @see com.adobe.aem.guides.udcp.core.models.Faculty#getDesignation()
     */
    @Override
    public String getDesignation() {
        return designation;
    }
 
    /**    (non-Javadoc)
     * Returns the faculty members school.
     * 
     * @return faculty school
     * @see com.adobe.aem.guides.udcp.core.models.Faculty#getSchool()
     */
    @Override
    public String getSchool() {
        return school;
    }

    
    /**    (non-Javadoc)
     * Returns the faculty members photo.
     * 
     * @return faculty photo
   
     */
    @Override
    public String getFacultyPhoto() {
        return facultyPhoto;
    }

       /**    (non-Javadoc)
     * Returns the faculty members qualifications.
     * 
     * @return faculty qualifications
   
     */
    @Override
    public String getQualifications() {
        return qualifications;
    }
 
    /**    (non-Javadoc)
     * Returns the faculty members bio.
     * 
     * @return faculty bio
   
     */
    @Override
    public String getBio() {
        return bio;
    }

    @Override
    public List<Faculty> getFacultyMembers() {
        return facultyMembers == null ? Collections.emptyList() : facultyMembers;
    }

    @Override
    public boolean isComposite() {
        return !getFacultyMembers().isEmpty();
    }
}
 