package com.test.gac_4.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actions_charite")
@Getter
@Setter
public class ActionCharite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column(nullable = false)
    private LocalDate dateAction;

    @Column(nullable = false)
    private String lieu;

    @Column(nullable = false)
    private Double objectifMontant;

    private Double montantCollecte = 0.0;

    private String image;
    private String video;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean archived = false;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isActive = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "organisation_id", nullable = false)
    private Organisation organisation;

    @OneToMany(mappedBy = "action", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Don> donations = new ArrayList<>();

    @OneToMany(mappedBy = "action", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participation> participations = new ArrayList<>();

    public Long getParticipantCount() {
        return participations != null ? (long) participations.size() : 0L;
    }

    public Double getProgressPercentage() {
        if (objectifMontant == 0) return 0.0;
        return (montantCollecte / objectifMontant) * 100;
    }
}
