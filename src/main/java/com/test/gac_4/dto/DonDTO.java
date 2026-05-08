package com.test.gac_4.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DonDTO {
    private Long id;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double montant;

    private String status;
    private String transactionId;
    private String paymentMethod;
    private String notes;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Action ID is required")
    private Long actionId;
}
