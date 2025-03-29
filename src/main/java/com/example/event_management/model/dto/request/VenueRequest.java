package com.example.event_management.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenueRequest {
    @NotBlank(message = "venue Name cannot be blank")
    @Schema(example = "banana")
    private String venueName;
    @NotBlank(message = "Location cannot be blank")
    @Schema(example = "KPS")
    private String location;
}
