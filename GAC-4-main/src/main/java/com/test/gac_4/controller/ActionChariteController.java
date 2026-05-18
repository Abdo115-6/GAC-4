package com.test.gac_4.controller;

import com.test.gac_4.dto.ActionChariteDTO;
import com.test.gac_4.dto.StatsDTO;
import com.test.gac_4.services.ActionChariteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/actions")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class ActionChariteController {
    @Autowired
    private ActionChariteService actionService;

    @PostMapping
    public ResponseEntity<ActionChariteDTO> createAction(@Valid @RequestBody ActionChariteDTO dto) {
        ActionChariteDTO created = actionService.createAction(dto);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActionChariteDTO> getAction(@PathVariable Long id) {
        ActionChariteDTO action = actionService.getActionById(id);
        return ResponseEntity.ok(action);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActionChariteDTO> updateAction(@PathVariable Long id, @RequestBody ActionChariteDTO dto) {
        ActionChariteDTO updated = actionService.updateAction(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/{id}/archive")
    public ResponseEntity<Void> archiveAction(@PathVariable Long id) {
        actionService.archiveAction(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ActionChariteDTO>> getAllActions() {
        List<ActionChariteDTO> actions = actionService.getAllActiveActions();
        return ResponseEntity.ok(actions);
    }

    @GetMapping("/public/active")
    public ResponseEntity<List<ActionChariteDTO>> getActiveActions() {
        List<ActionChariteDTO> actions = actionService.getAllActiveActions();
        return ResponseEntity.ok(actions);
    }

    @GetMapping("/public/popular")
    public ResponseEntity<List<ActionChariteDTO>> getPopularActions() {
        List<ActionChariteDTO> actions = actionService.getPopularActions();
        return ResponseEntity.ok(actions);
    }

    @GetMapping("/organisation/{orgId}")
    public ResponseEntity<List<ActionChariteDTO>> getActionsByOrganisation(@PathVariable Long orgId) {
        List<ActionChariteDTO> actions = actionService.getActionsByOrganisation(orgId);
        return ResponseEntity.ok(actions);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ActionChariteDTO>> getActionsByCategory(@PathVariable String category) {
        List<ActionChariteDTO> actions = actionService.getActionsByCategory(category);
        return ResponseEntity.ok(actions);
    }

    @GetMapping("/public/stats")
    public ResponseEntity<StatsDTO> getPublicStats() {
        StatsDTO stats = actionService.getPublicStats();
        return ResponseEntity.ok(stats);
    }
}
