package com.example.event_management.controller;

import com.example.event_management.model.entity.Venue;
import com.example.event_management.model.dto.request.VenueRequest;
import com.example.event_management.model.dto.response.ApiResponse;
import com.example.event_management.services.serviceInterface.VenueService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(@RequestParam (defaultValue = "1") @Positive Integer offset , @RequestParam (defaultValue = "10") @Positive Integer limit) {
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
    public ResponseEntity<ApiResponse<Venue>> getVenueById(@PathVariable("venue-id") @Positive Integer venueId) {
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
    public ResponseEntity<ApiResponse<Venue>> addVenue(@RequestBody @Valid VenueRequest venueRequest) {
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Add venue successfully")
                .payload(venueService.addVenue(venueRequest))
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Update Venue
    @PutMapping("{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> updateVenueById (@RequestBody @Valid VenueRequest venueRequest, @PathVariable("venue-id") @Positive Integer venueId) {
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Update venue" + venueId + "successfully")
                .payload(venueService.updateVenueById(venueRequest,venueId))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Delete Venue
    @DeleteMapping("{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> deleteVenueById(@PathVariable("venue-id") @Positive Integer venueId) {
        venueService.deleteVenueById(venueId);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Delete venue" + venueId + "successfully")
                .payload(null)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
