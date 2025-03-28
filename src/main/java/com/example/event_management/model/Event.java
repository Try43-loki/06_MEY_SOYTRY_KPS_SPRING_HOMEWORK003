package com.example.event_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    private Integer eventId;
    private String eventName;
    private LocalDateTime eventDate;
    private Integer venueId;
    private List<Venue> venueList;
    private List<Attendee> attendeeList;

}
