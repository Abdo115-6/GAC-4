package com.test.gac_4.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrganisationDTO {
    private Long id;

    @NotBlank(message = "Organization name is required")
    private String nom;

    @NotBlank(message = "Legal address is required")
    private String adresse;

    @NotBlank(message = "Email is required")
    private String email;

    private String phone;
    private String taxId;
    private String logo;
    private String description;
    private String website;
    private Boolean valide;
    private Boolean isActive;
    private Long adminId;
}
