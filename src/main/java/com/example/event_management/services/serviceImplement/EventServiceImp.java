package com.example.event_management.services.serviceImplement;

import com.example.event_management.exception.UserNotFoundException;
import com.example.event_management.model.entity.Attendee;
import com.example.event_management.model.entity.Event;
import com.example.event_management.model.entity.Venue;
import com.example.event_management.model.dto.request.EventRequest;
import com.example.event_management.repository.AttendeeRepository;
import com.example.event_management.repository.EventRepository;
import com.example.event_management.repository.VenueRepository;
import com.example.event_management.services.serviceInterface.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImp implements EventService {

    //-- inject event repository
    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;
    private final VenueRepository venueRepository;

    public EventServiceImp(EventRepository eventRepository, AttendeeRepository attendeeRepository, VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.attendeeRepository = attendeeRepository;
        this.venueRepository = venueRepository;
    }

    //-- get all events
    @Override
    public List<Event> getAllEvents(Integer offset, Integer limit) {
        return eventRepository.findAllEvents(offset, limit);
    }

    //-- get event by id
    @Override
    public Event getEventById(Integer eventId) {
      Event event = eventRepository.findEventById(eventId);
      if (event == null) {
          throw new UserNotFoundException("Event ID " + eventId + " not found");
      }
      return event;
    }

    //-- post event
    @Override
    public Event addEvent(EventRequest eventRequest) {
        // check venue id
        Integer venueId = eventRequest.getVenueId();

        Venue venue = venueRepository.findVenueById(venueId);
        if (venue == null) {
            throw new UserNotFoundException("Venue ID " + venueId + " not found");
        }

        // add event info
        Integer eventId = eventRepository.insertEvent(eventRequest);
        // add attendee
        for (Integer attendeeId : eventRequest.getAttendeesId()) {
            Attendee attendee = attendeeRepository.findAttendeeById(attendeeId);

            if (attendee == null) {
                throw new UserNotFoundException("Attendee ID " + attendeeId + " not found");
            }
            // insert attendee to event
            eventRepository.insertAttendee(eventId, attendeeId);
        }
        return eventRepository.findEventById(eventId);
    }

    @Override
    public Event updateEventById(EventRequest eventRequest, Integer id) {
        Integer venueId = eventRequest.getVenueId();
        Venue venue = venueRepository.findVenueById(venueId);
        if (venue == null) {
            throw new UserNotFoundException("Venue ID " + venueId + " not found");
        }
        // update event info
        Integer eventId = eventRepository.updateEventById(eventRequest, id);
        // delete attendee
        if (eventId == null) {
            throw new UserNotFoundException("Event ID " + id + "not found");
        } else {
            eventRepository.deleteAttendeeById(id);
        }
        // check venue id

        for (Integer attendeeId : eventRequest.getAttendeesId()) {
            Attendee attendee = attendeeRepository.findAttendeeById(attendeeId);
            if (attendee == null) {
                throw new UserNotFoundException("Attendee ID " + attendeeId + " not found");
            }
            eventRepository.insertAttendee(eventId, attendeeId);
        }
        return eventRepository.findEventById(eventId);

    }

    @Override
    public void deleteEventById(Integer eventId) {
        if(eventRepository.findEventById(eventId) == null) {
            throw new UserNotFoundException("Event ID " + eventId + " not found");
        }
        eventRepository.deleteEventById(eventId);
    }


}
