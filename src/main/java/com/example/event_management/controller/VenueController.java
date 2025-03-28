package com.example.event_management.controller;

import com.example.event_management.exception.UserNotFoundException;
import com.example.event_management.model.Venue;
import com.example.event_management.model.request.VenueRequest;
import com.example.event_management.model.response.ApiResponse;
import com.example.event_management.services.serviceInterface.VenueService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/venue")
public class VenueController {

    // inject Venue Service
    private final VenueService venueService;
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    // get all venue
    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(@RequestParam (defaultValue = "1") Integer offset , @RequestParam (defaultValue = "10") Integer limit) {
         ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                 .message("Get all venues successfully")
                 .payload(venueService.getAllVenues(offset, limit))
                 .status(HttpStatus.OK)
                 .timestamp(LocalDateTime.now())
                 .build();
         return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // get Venue by id
    @GetMapping("{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(@PathVariable("venue-id") Integer venueId) {
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Get venue" + venueId + "successfully")
                .payload(venueService.getVenueById(venueId))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Post Venue
    @PostMapping
    public ResponseEntity<ApiResponse<Venue>> addVenue(@RequestBody VenueRequest venueRequest) {
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Add venue successfully")
                .payload(venueService.addVenue(venueRequest))
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
