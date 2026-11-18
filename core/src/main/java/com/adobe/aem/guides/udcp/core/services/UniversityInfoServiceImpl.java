 
package com.adobe.aem.guides.udcp.core.services;
 
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
 
/**
 * Implementation of the University Information OSGi service.
 */
@Component(service = UniversityInfoService.class)
@Designate(ocd = UniversityInfoConfig.class)
public class UniversityInfoServiceImpl implements UniversityInfoService {
 
    private String universityName;
    private String contactEmail;
 
    /**
     * Initializes the service using OSGi configuration.
     *
     * @param config university information configuration
     */
    @Activate
    public void activate(UniversityInfoConfig config) {
        universityName = config.universityName();
        contactEmail = config.contactEmail();
    }
 
    @Override
    public String getUniversityName() {
        return universityName;
    }
 
    @Override
    public String getContactEmail() {
        return contactEmail;
    }
}
 
