package com.test.gac_4.entities;
import java.time.LocalDateTime;

public class Participation {
    private Long id;
    private LocalDateTime dateParticipation; // au lieu de Date

    public void participer() {
        this.dateParticipation = LocalDateTime.now();

    }
}