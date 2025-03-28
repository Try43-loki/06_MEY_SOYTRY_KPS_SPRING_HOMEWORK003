package com.example.event_management.services.serviceImplement;

import com.example.event_management.exception.UserNotFoundException;
import com.example.event_management.model.Attendee;
import com.example.event_management.model.Event;
import com.example.event_management.model.request.EventRequest;
import com.example.event_management.repository.AttendeeRepository;
import com.example.event_management.repository.EventRepository;
import com.example.event_management.services.serviceInterface.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventServiceImp implements EventService {

    //-- inject event repository
    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;
    public EventServiceImp(EventRepository eventRepository, AttendeeRepository attendeeRepository) {
        this.eventRepository = eventRepository;
        this.attendeeRepository = attendeeRepository;
    }

    //-- get all events
    @Override
    public List<Event> getAllEvents(Integer offset, Integer limit) {
        return eventRepository.findAllEvents(offset, limit);
    }
    //-- get event by Id
    @Override
    public Event getEventById(Integer eventId) {
        return eventRepository.findEventById(eventId);
    }

    //-- post event
    @Override
    public Event addEvent(EventRequest eventRequest) {
        // add event info
        Integer eventId = eventRepository.insertEvent(eventRequest);
        // add attendee
        for(Integer attendeeId : eventRequest.getAttendeesId()){
            Attendee attendee = attendeeRepository.findAttendeeById(attendeeId);
            if(attendee == null){
                throw new RuntimeException("Attendee ID + "+attendeeId+"+ not found");
            }
            // insert attendee to event
            eventRepository.insertAttendee(eventId,attendeeId);
        }
        return eventRepository.findEventById(eventId);
    }

    @Override
    public Event updateEventById(EventRequest eventRequest, Integer id) {
        // update event info
        Integer eventId = eventRepository.updateEventById(eventRequest, id);
        // delete attendee
        if(eventId == null){
            throw new UserNotFoundException("Event ID " + id + "not found");
        }else{
            eventRepository.deleteAttendeeById(id);
        }
        for(Integer attendeeId : eventRequest.getAttendeesId()){
            Attendee attendee = attendeeRepository.findAttendeeById(id);
            if(attendee == null){
                throw new RuntimeException("Attendee ID " + id + "not found");
            }
            eventRepository.insertAttendee(eventId,attendeeId);
        }
        return eventRepository.findEventById(eventId);

    }

    @Override
    public void deleteEventById(Integer eventId) {
        eventRepository.deleteEventById(eventId);
    }


}
