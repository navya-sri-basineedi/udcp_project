 
package com.adobe.aem.guides.udcp.core.models.impl;
 
import com.adobe.aem.guides.udcp.core.models.Course;
import com.adobe.aem.guides.udcp.core.models.Highlight;
import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
 
@Model(
    adaptables = Resource.class,
    adapters = Course.class,
    resourceType = "/apps/udcp/components/udcp-components/course-highlights",
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CourseImpl implements Course {
 
    @ValueMapValue
    private String heading;
 
    @ValueMapValue
    private String description;
 
    @ChildResource
    private List<Highlight> highlights;
 
    @Override
    public String getHeading() {
        return heading;
    }
 
    @Override
    public String getDescription() {
        return description;
    }
 
    @Override
    public List<Highlight> getHighlights() {
        return highlights;
    }
}