package com.adobe.aem.guides.udcp.core.models;
 
import java.util.List;
 
public interface Footer {
 
    String getUniversityName();
 
    String getDescription();
 
    String getCopyrightText();
 
    List<QuickLink> getQuickLinks();
}
 