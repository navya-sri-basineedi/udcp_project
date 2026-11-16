package com.adobe.aem.guides.udcp.core.models;
import java.util.List;


public interface Course {
    String getHeading();
    String getDescription();
    List<Highlight> getHighlights();
  
}