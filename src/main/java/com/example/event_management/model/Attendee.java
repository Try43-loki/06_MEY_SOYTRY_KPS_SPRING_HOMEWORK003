package com.example.event_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Attendee {
    private int attendeeId;
    private String attendeeName;
    private String email;
}
