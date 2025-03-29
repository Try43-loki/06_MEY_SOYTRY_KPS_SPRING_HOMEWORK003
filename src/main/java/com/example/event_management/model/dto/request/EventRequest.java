package com.example.event_management.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Event Name cannot be blank")
    @Schema(example = "concert")
    private String eventName;
    @NotBlank(message = "Event Date cannot be blank")
    @Schema(example = "dd-mm-yyyy")
    private String eventDate;
    @Min(value = 1 , message = "Venue Id must be grater than 0")
    @Schema(example = "0")
    private Integer venueId;
    @NotEmpty(message = "Attendees Id cannot be empty")
    @Schema(example = "[0]")
    private List<Integer> attendeesId;
}
