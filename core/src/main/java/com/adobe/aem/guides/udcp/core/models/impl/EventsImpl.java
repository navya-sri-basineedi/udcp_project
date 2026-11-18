package com.adobe.aem.guides.udcp.core.models.impl;
 
import com.adobe.aem.guides.udcp.core.models.Events;
import com.adobe.aem.guides.udcp.core.models.Event;
 
import java.util.List;
 
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
 
@Model(
    adaptables = Resource.class,
    adapters = Events.class,
    resourceType = "/apps/udcp/components/udcp-components/upcoming-events",
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

/**
 * Sling Model implementation for the Events Component.
 * Retrieves event information from Aem resources.
 * 
 * @author
 */
public class EventsImpl implements Events {
 
    @ValueMapValue
    private String heading;
 
    @ChildResource
    private List<Event> events;
 
    @Override
    public String getHeading() {
        return heading;
    }
 
    @Override
    public List<Event> getEvents() {
        return events;
    }
}
