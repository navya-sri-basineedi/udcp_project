package com.adobe.aem.guides.udcp.core.models.impl;
 
import com.adobe.aem.guides.udcp.core.models.QuickLink;
 
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
 
@Model(
    adaptables = Resource.class,
    adapters = QuickLink.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class QuickLinkImpl implements QuickLink {
 
    @ValueMapValue
    private String label;
 
    @ValueMapValue
    private String link;
 
    @Override
    public String getLabel() {
        return label;
    }
 
    @Override
    public String getLink() {
        return link;
    }
}
 