package com.test.gac_4.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ActionChariteDTO {
    private Long id;

    @NotBlank(message = "Title is required")
    private String titre;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Action date is required")
    private LocalDate dateAction;

    @NotBlank(message = "Location is required")
    private String lieu;

    @NotNull(message = "Fundraising goal is required")
    private Double objectifMontant;

    private Double montantCollecte;

    private String image;
    private String video;
    private Boolean archived;
    private Boolean isActive;

    @NotNull(message = "Organization ID is required")
    private Long organisationId;

    private Long participantCount;
    private Double progressPercentage;
    private String organisationName;
}
