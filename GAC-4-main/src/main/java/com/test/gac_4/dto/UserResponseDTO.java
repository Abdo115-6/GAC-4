package com.test.gac_4.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String avatar;
    private String bio;
    private Boolean isAdmin;
    private Boolean isActive;
}
