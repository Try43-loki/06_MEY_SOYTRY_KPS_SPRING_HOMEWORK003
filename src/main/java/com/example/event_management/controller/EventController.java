package com.example.event_management.controller;

import com.example.event_management.model.entity.Event;
import com.example.event_management.model.dto.request.EventRequest;
import com.example.event_management.model.dto.response.ApiResponse;
import com.example.event_management.services.serviceInterface.EventService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents(@RequestParam(defaultValue = "1") @Positive Integer offset, @RequestParam(defaultValue = "10") @Positive Integer limit) {
        ApiResponse<List<Event>> response = ApiResponse.<List<Event>>builder()
                .message("Get all events successfully")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .payload(eventService.getAllEvents(offset, limit))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //-- get event by id
    @GetMapping("{event-Id}")
    public ResponseEntity<ApiResponse<Event>> getEvent(@PathVariable("event-Id") @Positive Integer eventId) {
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
    public ResponseEntity<ApiResponse<Event>> addEvent(@RequestBody @Valid EventRequest eventRequest) {
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
    public ResponseEntity<ApiResponse<Event>> updateEventById(@RequestBody @Valid EventRequest eventRequest, @PathVariable("event-Id") @Positive Integer eventId) {
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Get all events successfully")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .payload(eventService.updateEventById(eventRequest, eventId))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //-- delete event
    @DeleteMapping("{event-id}")
    ResponseEntity<ApiResponse<Event>> deleteEventById(@PathVariable("event-id") @Positive Integer eventId) {
        eventService.deleteEventById(eventId);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Delete Event Id " + eventId + " successfully")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .payload(null)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
