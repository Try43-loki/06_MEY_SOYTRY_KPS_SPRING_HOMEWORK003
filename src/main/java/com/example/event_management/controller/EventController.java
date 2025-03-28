package com.example.event_management.controller;

import com.example.event_management.model.Attendee;
import com.example.event_management.model.Event;
import com.example.event_management.model.request.EventRequest;
import com.example.event_management.model.response.ApiResponse;
import com.example.event_management.repository.EventRepository;
import com.example.event_management.services.serviceInterface.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/event")
public class EventController {

    //-- inject event service
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    //-- get all events
    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents(@RequestParam (defaultValue = "1") Integer offset, @RequestParam (defaultValue = "10") Integer limit) {
        ApiResponse<List<Event>> response = ApiResponse.<List<Event>>builder()
                .message("Get all events successfully")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .payload(eventService.getAllEvents(offset, limit))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //-- get event by Id
    @GetMapping("{event-Id}")
    public ResponseEntity<ApiResponse<Event>> getEvent(@PathVariable("event-Id") Integer eventId) {
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Get all events successfully")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .payload(eventService.getEventById(eventId))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //-- Post event
    @PostMapping
    public ResponseEntity<ApiResponse<Event>> addEvent(@RequestBody EventRequest eventRequest) {
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Get all events successfully")
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .payload(eventService.addEvent(eventRequest))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //-- Update event
    @PutMapping("{event-Id}")
    public ResponseEntity<ApiResponse<Event>> updateEventById(@RequestBody EventRequest eventRequest, @PathVariable("event-Id") Integer eventId) {
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Get all events successfully")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .payload(eventService.updateEventById(eventRequest,eventId))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //-- delete event
    @DeleteMapping("{event-id}")
    ResponseEntity<ApiResponse<Event>> deleteEventById(@PathVariable("event-id") Integer eventId) {
        eventService.deleteEventById(eventId);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Delete Event Id "+eventId+" successfully")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .payload(null)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
