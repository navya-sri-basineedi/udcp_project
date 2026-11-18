 
package com.adobe.aem.guides.udcp.core.models.impl;
 
import com.adobe.aem.guides.udcp.core.models.Course;
import com.adobe.aem.guides.udcp.core.services.CourseHighlightsService;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
 
@ExtendWith(AemContextExtension.class)
class CourseImplTest {

    private CourseHighlightsService registerCourseHighlightsService(AemContext context) {
        CourseHighlightsService service = new CourseHighlightsService() {
            @Override
            public String getSectionHeading() {
                return "Configured Course Heading";
            }

            @Override
            public String getSectionDescription() {
                return "Configured Course Description";
            }
        };
        context.registerService(CourseHighlightsService.class, service);
        return service;
    }

    @Test
    void testConfiguredHeadingAndDescriptionFallback(AemContext context) {
        registerCourseHighlightsService(context);
        context.addModelsForClasses(CourseImpl.class);

        Resource courseResource = context.create().resource("/content/course");
        Course course = courseResource.adaptTo(Course.class);

        assertNotNull(course);
        assertEquals("Configured Course Heading", course.getHeading());
        assertEquals("Configured Course Description", course.getDescription());
    }

    @Test
    void testAuthoredHeadingAndDescriptionTakePrecedence(AemContext context) {
        registerCourseHighlightsService(context);
        context.addModelsForClasses(CourseImpl.class);

        Resource courseResource = context.create().resource(
            "/content/course",
            "heading", "Authored Course Heading",
            "description", "Authored Course Description"
        );
        Course course = courseResource.adaptTo(Course.class);

        assertNotNull(course);
        assertEquals("Authored Course Heading", course.getHeading());
        assertEquals("Authored Course Description", course.getDescription());
    }
 
    @Test
    void testCourseHeading(AemContext context) {
 
        context.addModelsForClasses(CourseImpl.class);
 
        Resource courseResource = context.create().resource(
            "/content/course",
            "heading", "Explore Our Courses",
            "description", "Discover a wide range of industry-focused programs designed to build your skills, encourage innovation, and prepare you for successful careers."
        );
 
        Course course = courseResource.adaptTo(Course.class);
 
        assertNotNull(course);
        assertEquals("Explore Our Courses", course.getHeading());
    }
 
    @Test
    void testCourseDescription(AemContext context) {
 
        context.addModelsForClasses(CourseImpl.class);
 
        Resource courseResource = context.create().resource(
            "/content/course",
            "heading", "Explore Our Courses",
            "description", "Discover a wide range of industry-focused programs designed to build your skills, encourage innovation, and prepare you for successful careers."
        );
 
        Course course = courseResource.adaptTo(Course.class);
 
        assertNotNull(course);
        assertEquals(
            "Discover a wide range of industry-focused programs designed to build your skills, encourage innovation, and prepare you for successful careers.",
            course.getDescription()
        );
    }
}
 