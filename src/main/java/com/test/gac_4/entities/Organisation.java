package com.test.gac_4.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter
@Setter
public class Organisation {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nom;
        private String adresse;
        private String email;
        private boolean valide;

        public void createOrganisation() {}
        public void updateOrganisation() {}
    }

