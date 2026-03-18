package com.test.gac_4.entities;

import java.time.LocalDateTime;

public class Don {
    private Long id;
    private double montant;
    private LocalDateTime date; // au lieu de Date

    public void effectuerDon() {
        this.date = LocalDateTime.now(); // date actuelle
    }
}