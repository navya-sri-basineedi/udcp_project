package com.adobe.aem.guides.udcp.core.services;
 
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.AttributeDefinition;
 
@ObjectClassDefinition(name = "University Information Configuration")
public @interface UniversityInfoConfig {
 
    @AttributeDefinition(name = "University Name")
    String universityName() default "Campus Connect University";
 
    @AttributeDefinition(name = "Contact Email")
    String contactEmail() default "info@campusconnect.edu";
 
}
