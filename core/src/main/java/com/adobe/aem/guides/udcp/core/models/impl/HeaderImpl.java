

package com.adobe.aem.guides.udcp.core.models.impl;
 
import com.adobe.aem.guides.udcp.core.models.Header;
import com.adobe.aem.guides.udcp.core.models.NavigationLink;
 
import java.util.List;
 
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
 
@Model(
    adaptables = Resource.class,
    adapters = Header.class,
    resourceType = "/apps/udcp/components/udcp-components/header",
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

/**
 * Sling Model implementation for the Header Component.
 * Retrieves header information from Aem resources.
 * 
 * @author
 */
public class HeaderImpl implements Header {
 
    @ValueMapValue
    private String logo;
 
    @ValueMapValue
    private String universityName;
 
    @ValueMapValue
    private String tagline;
 
    @ChildResource
    private List<NavigationLink> navigationLinks;
 
    @ValueMapValue
    private String buttonLabel;
 
    @ValueMapValue
    private String buttonLink;

    @ValueMapValue
    private String signInLink;
 
    @Override
    public String getLogo() {
        return logo;
    }
 
    @Override
    public String getUniversityName() {
        return universityName;
    }
 
    @Override
    public String getTagline() {
        return tagline;
    }
 
    @Override
    public List<NavigationLink> getNavigationLinks() {
        return navigationLinks;
    }
 
    @Override
    public String getButtonLabel() {
        return buttonLabel;
    }
 
    @Override
    public String getButtonLink() {
        return buttonLink;
    }

    @Override
    public String getSignInLink() {
        return signInLink;
    }
}
