 
package com.adobe.aem.guides.udcp.core.models.impl;
 
import com.adobe.aem.guides.udcp.core.models.Footer;
import com.adobe.aem.guides.udcp.core.models.QuickLink;
 
import java.util.List;
 
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
 
@Model(
    adaptables = Resource.class,
    adapters = Footer.class,
    resourceType = "/apps/udcp/components/udcp-components/footer",
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class FooterImpl implements Footer {
 
    @ValueMapValue
    private String universityName;
 
    @ValueMapValue
    private String description;
 
    @ValueMapValue
    private String copyrightText;
 
    @ChildResource
    private List<QuickLink> quickLinks;
 
    @Override
    public String getUniversityName() {
        return universityName;
    }
 
    @Override
    public String getDescription() {
        return description;
    }
 
    @Override
    public String getCopyrightText() {
        return copyrightText;
    }
 
    @Override
    public List<QuickLink> getQuickLinks() {
        return quickLinks;
    }
}
 