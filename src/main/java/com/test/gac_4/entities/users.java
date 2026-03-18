package com.test.gac_4.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity @Getter
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer age;
}
