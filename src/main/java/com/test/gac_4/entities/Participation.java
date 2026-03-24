package com.test.gac_4.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity @Getter
@Setter
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateParticipation; // au lieu de Date
    @ManyToOne
    private ActionCharite actionCharite;
    public void participer() {
        this.dateParticipation = LocalDateTime.now();

    }
}