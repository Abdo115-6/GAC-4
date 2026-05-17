package com.test.gac_4.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organisations")
@Getter
@Setter
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nom;

    @Column(nullable = false)
    private String adresse;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;
    private String taxId;
    private String logo;
    private String description;
    private String website;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean valide = false;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isActive = true;

    @OneToOne
    @JoinColumn(name = "admin_id", unique = true)
    private users admin;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActionCharite> actions = new ArrayList<>();
}


