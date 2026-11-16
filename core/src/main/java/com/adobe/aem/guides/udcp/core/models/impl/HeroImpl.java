package com.adobe.aem.guides.udcp.core.models.impl;
 
import com.adobe.aem.guides.udcp.core.models.Hero;
 
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
 
@Model(
    adaptables = Resource.class,
    adapters = Hero.class,
    resourceType = "/apps/udcp/components/udcp-components/hero-banner",
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HeroImpl implements Hero {
 
    @ValueMapValue
    private String backgroundImage;
 
    @ValueMapValue
    private String headline;
 
    @ValueMapValue
    private String description;
 
    @ValueMapValue
    private String buttonLabel;
 
    @ValueMapValue
    private String buttonLink;
 
    @Override
    public String getBackgroundImage() {
        return backgroundImage;
    }
 
    @Override
    public String getHeadline() {
        return headline;
    }
 
    @Override
    public String getDescription() {
        return description;
    }
 
    @Override
    public String getButtonLabel() {
        return buttonLabel;
    }
 
    @Override
    public String getButtonLink() {
        return buttonLink;
    }
}
 