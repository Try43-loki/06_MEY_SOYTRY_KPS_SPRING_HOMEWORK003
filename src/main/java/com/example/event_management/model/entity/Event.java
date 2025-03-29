package com.example.event_management.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    private Integer eventId;
    private String eventName;
    private String eventDate;
    private Venue venue;
    private List<Attendee> attendeeList;

}
