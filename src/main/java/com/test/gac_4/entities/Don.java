package com.test.gac_4.entities;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter
@Setter
public class Don {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double montant;
    private LocalDateTime date; // au lieu de Date

    public void effectuerDon() {
        this.date = LocalDateTime.now(); // date actuelle
    }
}
