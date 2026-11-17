package com.adobe.aem.guides.udcp.core.models.impl;
 
import com.adobe.aem.guides.udcp.core.models.NavigationLink;
 
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
 
@Model(
    adaptables = Resource.class,
    adapters = NavigationLink.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class NavigationLinkImpl implements NavigationLink {
 
    @ValueMapValue
    private String label;
 
    @ValueMapValue
    private String link;

    @ValueMapValue
    private String parentLabel;
 
    @Override
    public String getLabel() {
        return label;
    }
 
    @Override
    public String getLink() {
        return link;
    }

    @Override
    public String getParentLabel() {
        return parentLabel;
    }
}