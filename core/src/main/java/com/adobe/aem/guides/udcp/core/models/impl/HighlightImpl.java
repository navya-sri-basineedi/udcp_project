 
package com.adobe.aem.guides.udcp.core.models.impl;
 
import com.adobe.aem.guides.udcp.core.models.Highlight;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
 
@Model(
    adaptables = Resource.class,
    adapters = Highlight.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HighlightImpl implements Highlight {
 
    @ValueMapValue
    private String highlight;
 
    @ValueMapValue
    private String duration;
 
    @ValueMapValue
    private String fees;
 
    @Override
    public String getHighlight() {
        return highlight;
    }
 
    @Override
    public String getDuration() {
        return duration;
    }
 
    @Override
    public String getFees() {
        return fees;
    }
}
 