package com.example.event_management.services.serviceInterface;

import com.example.event_management.model.Attendee;
import com.example.event_management.model.request.AttendeeRequest;
import com.example.event_management.model.request.VenueRequest;

import java.util.List;

public interface AttendeeService {

    List<Attendee> getAllAttendees(Integer offset,Integer limit);
    Attendee getAttendeeById(Integer attendeeId);
    Attendee addAttendee(AttendeeRequest attendeeRequest);
    Attendee updateAttendeeById(AttendeeRequest attendeeRequest, Integer attendeeId);
    void deleteAttendeeById(Integer attendeeId);

}
