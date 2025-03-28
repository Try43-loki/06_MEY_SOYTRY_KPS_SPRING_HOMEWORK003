package com.example.event_management.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventRequest {
    private String eventName;
    private String eventDate;
    private Integer venueId;
    private List<Integer> attendeesId;
}
