package com.adobe.aem.guides.udcp.core.services;

/**
 * Provides reusable configuration values for the Course Highlights component.
 */
public interface CourseHighlightsService {

    /**
     * Gets the configured section heading.
     *
     * @return section heading
     */
    String getSectionHeading();

    /**
     * Gets the configured section description.
     *
     * @return section description
     */
    String getSectionDescription();
}
