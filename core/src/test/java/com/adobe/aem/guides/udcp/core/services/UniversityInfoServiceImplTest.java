package com.adobe.aem.guides.udcp.core.services;
 
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
 
@ExtendWith(AemContextExtension.class)
class UniversityInfoServiceImplTest {
 
    @Test
    void testUniversityInformationService(AemContext context) {
 
        UniversityInfoServiceImpl service =
                new UniversityInfoServiceImpl();
 
        context.registerInjectActivateService(
                service,
                "universityName", "Campus Connect University",
                "contactEmail", "info@campusconnect.edu"
        );
 
        assertEquals(
                "Campus Connect University",
                service.getUniversityName()
        );
 
        assertEquals(
                "info@campusconnect.edu",
                service.getContactEmail()
        );
    }
}
 