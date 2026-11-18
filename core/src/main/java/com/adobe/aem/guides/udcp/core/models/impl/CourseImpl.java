 
package com.adobe.aem.guides.udcp.core.models.impl;
 
import com.adobe.aem.guides.udcp.core.models.Course;
import com.adobe.aem.guides.udcp.core.models.Highlight;
import com.adobe.aem.guides.udcp.core.services.CourseHighlightsService;
import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
 
@Model(
    adaptables = Resource.class,
    adapters = Course.class,
    resourceType = "/apps/udcp/components/udcp-components/course-highlights",
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

/**
 * Sling Model implementation for the Course Component.
 * Retrieves course information from Aem resources.
 * 
 * @author
 */
public class CourseImpl implements Course {

    @OSGiService
    private CourseHighlightsService courseHighlightsService;
 
    @ValueMapValue
    private String heading;
 
    @ValueMapValue
    private String description;
 
    @ChildResource
    private List<Highlight> highlights;
 
    
    /**    (non-Javadoc)
     * Returns the course heading
     * 
     * @return course heading
   
     */
    @Override
    public String getHeading() {
        if (heading != null && !heading.trim().isEmpty()) {
            return heading;
        }
        return courseHighlightsService != null ? courseHighlightsService.getSectionHeading() : null;
    }
 
    /**    (non-Javadoc)
     * Returns the course description
     * 
     * @return course description
   
     */
    @Override
    public String getDescription() {
        if (description != null && !description.trim().isEmpty()) {
            return description;
        }
        return courseHighlightsService != null ? courseHighlightsService.getSectionDescription() : null;
    }
 
    /**    (non-Javadoc)
     * Returns the course highlights
     * 
     * @return course highlights
   
     */
    @Override
    public List<Highlight> getHighlights() {
        return highlights;
    }
}