package com.test.gac_4.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter
@Setter
public class ActionCharite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private double objectifMontant;
    private double montantCollecte;

    public void createAction() {}
    public void updateAction() {}
    public void suivreProgression() {}
}
