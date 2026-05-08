package com.test.gac_4.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "participations")
@Getter
@Setter
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private String notes;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private users user;

    @ManyToOne
    @JoinColumn(name = "action_id", nullable = false)
    private ActionCharite action;

    public void participer() {
        this.createdAt = LocalDateTime.now();
    }
}