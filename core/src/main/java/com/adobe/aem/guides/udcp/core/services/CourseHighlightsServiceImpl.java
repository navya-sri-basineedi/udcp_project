package com.adobe.aem.guides.udcp.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

/**
 * OSGi implementation of the Course Highlights configuration service.
 */
@Component(service = CourseHighlightsService.class)
@Designate(ocd = CourseHighlightsConfig.class)
public class CourseHighlightsServiceImpl implements CourseHighlightsService {

    private String sectionHeading;
    private String sectionDescription;

    /**
     * Activates the service from the configured metatype values.
     *
     * @param config Course Highlights OSGi configuration
     */
    @Activate
    public void activate(CourseHighlightsConfig config) {
        sectionHeading = config.sectionHeading();
        sectionDescription = config.sectionDescription();
    }

    @Override
    public String getSectionHeading() {
        return sectionHeading;
    }

    @Override
    public String getSectionDescription() {
        return sectionDescription;
    }
}
