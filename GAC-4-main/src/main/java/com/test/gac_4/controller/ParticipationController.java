package com.test.gac_4.controller;

import com.test.gac_4.dto.ParticipationDTO;
import com.test.gac_4.services.ParticipationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participations")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class ParticipationController {
    @Autowired
    private ParticipationService participationService;

    @PostMapping
    public ResponseEntity<ParticipationDTO> joinAction(@Valid @RequestBody ParticipationDTO dto) {
        ParticipationDTO created = participationService.joinAction(dto);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipationDTO> getParticipation(@PathVariable Long id) {
        ParticipationDTO participation = participationService.getParticipation(id);
        return ResponseEntity.ok(participation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> leaveAction(@PathVariable Long id) {
        participationService.leaveAction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ParticipationDTO>> getUserParticipations(@PathVariable Long userId) {
        List<ParticipationDTO> participations = participationService.getParticipationsByUser(userId);
        return ResponseEntity.ok(participations);
    }

    @GetMapping("/action/{actionId}")
    public ResponseEntity<List<ParticipationDTO>> getActionParticipations(@PathVariable Long actionId) {
        List<ParticipationDTO> participations = participationService.getParticipationsByAction(actionId);
        return ResponseEntity.ok(participations);
    }

    @GetMapping("/action/{actionId}/count")
    public ResponseEntity<Long> getParticipantCount(@PathVariable Long actionId) {
        Long count = participationService.getActionParticipantCount(actionId);
        return ResponseEntity.ok(count);
    }
}