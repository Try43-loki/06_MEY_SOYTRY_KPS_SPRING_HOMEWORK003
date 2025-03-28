package com.example.event_management.services.serviceInterface;

import com.example.event_management.model.Event;
import com.example.event_management.model.request.EventRequest;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(Integer offset, Integer limit);
    Event getEventById(Integer eventId);
    Event addEvent(EventRequest eventRequest);
    Event updateEventById(EventRequest eventRequest, Integer eventId);
    void deleteEventById(Integer eventId);
}
