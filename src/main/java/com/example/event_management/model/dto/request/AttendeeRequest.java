package com.example.event_management.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendeeRequest {
    @NotBlank(message = "Attendee Name cannot be blank")
    @Schema(example = "banana")
    private String attendeeName;
    @Email(message = "must be a well-formed email address")
    @NotBlank(message = "Email cannot be blank")
    @Schema(example = "email")
    private String email;
}
