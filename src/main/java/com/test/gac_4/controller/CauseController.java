package com.test.gac_4.controller;

import com.test.gac_4.dto.CauseDTO;
import com.test.gac_4.services.CauseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/causes")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class CauseController {

    @Autowired
    private CauseService causeService;

    @GetMapping
    public ResponseEntity<List<CauseDTO>> getAllCauses() {
        List<CauseDTO> causes = causeService.getAllCauses();
        return ResponseEntity.ok(causes);
    }

    @PostMapping
    public ResponseEntity<CauseDTO> addCause(@RequestBody CauseDTO causeDTO) {
        CauseDTO createdCause = causeService.addCause(causeDTO);
        return ResponseEntity.status(201).body(createdCause);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CauseDTO> getCauseById(@PathVariable Long id) {
        CauseDTO cause = causeService.getCauseById(id);
        return ResponseEntity.ok(cause);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CauseDTO> updateCause(@PathVariable Long id, @RequestBody CauseDTO causeDTO) {
        CauseDTO updatedCause = causeService.updateCause(id, causeDTO);
        return ResponseEntity.ok(updatedCause);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCause(@PathVariable Long id) {
        causeService.deleteCause(id);
        return ResponseEntity.noContent().build();
    }
}
