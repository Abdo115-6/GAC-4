package com.test.gac_4.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DonDTO {
    private Long id;

    @Positive(message = "Amount must be positive")
    private Double montant;

    private Double amount;

    private String status;
    private String transactionId;
    private String paymentMethod;
    private String notes;
    private String message;

    private Long userId;

    private Long actionId;
    private Long actionChariteId;

    private String userName;
    private String actionTitle;
    private String organisationName;
    private String createdAt;
}
