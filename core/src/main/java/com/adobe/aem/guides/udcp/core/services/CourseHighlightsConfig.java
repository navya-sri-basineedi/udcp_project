package com.adobe.aem.guides.udcp.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * OSGi configuration for reusable Course Highlights section content.
 */
@ObjectClassDefinition(
    name = "Campus Connect - Course Highlights Configuration",
    description = "Configures the default heading and description for Course Highlights components"
)
public @interface CourseHighlightsConfig {

    @AttributeDefinition(
        name = "Section Heading",
        description = "Default heading used when the component's Heading field is blank"
    )
    String sectionHeading() default "Explore Our Courses";

    @AttributeDefinition(
        name = "Section Description",
        description = "Default description used when the component's Description field is blank"
    )
    String sectionDescription() default "Discover a wide range of industry-focused programs designed to build your skills, encourage innovation, and prepare you for successful careers.";
}
