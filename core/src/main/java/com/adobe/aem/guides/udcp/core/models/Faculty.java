package com.adobe.aem.guides.udcp.core.models;

import java.util.List;

public interface Faculty {
    String getFacultyName();
    String getDesignation();
    String getSchool();
    String getFacultyPhoto();
    String getQualifications();
    String getBio();
    List<Faculty> getFacultyMembers();
    boolean isComposite();
}
