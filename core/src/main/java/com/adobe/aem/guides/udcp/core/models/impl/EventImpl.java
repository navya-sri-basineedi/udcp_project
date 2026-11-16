package com.adobe.aem.guides.udcp.core.models.impl;
 
import com.adobe.aem.guides.udcp.core.models.Event;
 
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
 
@Model(
    adaptables = Resource.class,
    adapters = Event.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class EventImpl implements Event {
 
    @ValueMapValue
    private String eventTitle;
 
    @ValueMapValue
    private String eventDate;
 
    @ValueMapValue
    private String eventLocation;
 
    @ValueMapValue
    private String eventDescription;
 
    @Override
    public String getEventTitle() {
        return eventTitle;
    }
 
    @Override
    public String getEventDate() {
        return eventDate;
    }
 
    @Override
    public String getEventLocation() {
        return eventLocation;
    }
 
    @Override
    public String getEventDescription() {
        return eventDescription;
    }
}
 