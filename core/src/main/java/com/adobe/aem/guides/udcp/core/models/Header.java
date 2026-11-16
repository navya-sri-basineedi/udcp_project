package com.adobe.aem.guides.udcp.core.models;
 
import java.util.List;
 
public interface Header {
 
    String getLogo();
 
    String getUniversityName();
 
    String getTagline();
 
    List<NavigationLink> getNavigationLinks();
 
    String getButtonLabel();
 
    String getButtonLink();
}
 