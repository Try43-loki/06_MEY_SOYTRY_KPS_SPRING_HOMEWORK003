package com.example.event_management.controller;

import com.example.event_management.model.entity.Attendee;
import com.example.event_management.model.dto.request.AttendeeRequest;
import com.example.event_management.model.dto.response.ApiResponse;
import com.example.event_management.services.serviceInterface.AttendeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/attendee")
public class AttendeeController {

    // inject Attendee Service
    private final AttendeeService attendeeService;
    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    // get all attendee
    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllVenues(@RequestParam(defaultValue = "1")  @Positive Integer offset , @RequestParam (defaultValue = "10") @Positive Integer limit) {
         ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>builder()
                 .message("Get all venues successfully")
                 .payload(attendeeService.getAllAttendees(offset, limit))
                 .status(HttpStatus.OK)
                 .timestamp(LocalDateTime.now())
                 .build();
         return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // get Attendee by id
    @GetMapping("{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> getVenueById(@PathVariable("attendee-id") @Positive Integer attendeeId) {
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Get attendee" + attendeeId + "successfully")
                .payload(attendeeService.getAttendeeById(attendeeId))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Post Attendee
    @PostMapping
    public ResponseEntity<ApiResponse<Attendee>> addVenue(@RequestBody @Valid AttendeeRequest attendeeRequest) {
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Add attendee successfully")
                .payload(attendeeService.addAttendee(attendeeRequest))
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Update Attendee
    @PutMapping("{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> updateVenueById (@PathVariable("attendee-id") @Positive Integer attendeeId,@RequestBody @Valid AttendeeRequest attendeeRequest ) {
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Update attendee" + attendeeId + "successfully")
                .payload(attendeeService.updateAttendeeById(attendeeRequest ,attendeeId))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Delete Attendee
    @DeleteMapping("{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> deleteVenueById(@PathVariable("attendee-id") @Positive Integer attendeeId) {
        attendeeService.deleteAttendeeById(attendeeId);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Delete attendee" + attendeeId + "successfully")
                .payload(null)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
