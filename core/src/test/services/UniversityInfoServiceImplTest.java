package com.adobe.aem.guides.udcp.core.services;
 
import org.junit.jupiter.api.Test;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
 
class UniversityInfoServiceImplTest {
 
    @Test
    void testUniversityInformationService() {
 
        UniversityInfoConfig config = new UniversityInfoConfig() {
 
            @Override
            public String universityName() {
                return "Campus Connect University";
            }
 
            @Override
            public String contactEmail() {
                return "info@campusconnect.edu";
            }
        };
 
        UniversityInfoServiceImpl service =
                new UniversityInfoServiceImpl();
 
        service.activate(config);
 
        assertEquals("Campus Connect University",
                service.getUniversityName());
 
        assertEquals("info@campusconnect.edu",
                service.getContactEmail());
    }
}