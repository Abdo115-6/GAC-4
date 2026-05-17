package com.test.gac_4.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParticipationDTO {
    private Long id;
    private String notes;
    private Boolean isActive;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Action ID is required")
    private Long actionId;
}
