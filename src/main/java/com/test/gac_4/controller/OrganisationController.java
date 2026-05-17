package com.test.gac_4.controller;

import com.test.gac_4.dto.OrganisationDTO;
import com.test.gac_4.services.OrganisationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/organisations")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class OrganisationController {
    @Autowired
    private OrganisationService organisationService;

    @PostMapping
    public ResponseEntity<OrganisationDTO> createOrganisation(@Valid @RequestBody OrganisationDTO dto) {
        OrganisationDTO created = organisationService.createOrganisation(dto);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganisationDTO> getOrganisation(@PathVariable Long id) {
        OrganisationDTO org = organisationService.getOrganisationById(id);
        return ResponseEntity.ok(org);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganisationDTO> updateOrganisation(@PathVariable Long id, @RequestBody OrganisationDTO dto) {
        OrganisationDTO updated = organisationService.updateOrganisation(id, dto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<List<OrganisationDTO>> getAllOrganisations() {
        List<OrganisationDTO> organisations = organisationService.getAllOrganisations();
        return ResponseEntity.ok(organisations);
    }

    @GetMapping("/public/validated")
    public ResponseEntity<List<OrganisationDTO>> getValidatedOrganisations() {
        List<OrganisationDTO> organisations = organisationService.getAllValidatedOrganisations();
        return ResponseEntity.ok(organisations);
    }

    @GetMapping("/admin/pending")
    public ResponseEntity<List<OrganisationDTO>> getPendingOrganisations() {
        List<OrganisationDTO> organisations = organisationService.getAllPendingOrganisations();
        return ResponseEntity.ok(organisations);
    }

    @PostMapping("/admin/{id}/validate")
    public ResponseEntity<Void> validateOrganisation(@PathVariable Long id) {
        organisationService.validateOrganisation(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/{id}/reject")
    public ResponseEntity<Void> rejectOrganisation(@PathVariable Long id) {
        organisationService.rejectOrganisation(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganisation(@PathVariable Long id) {
        organisationService.deleteOrganisation(id);
        return ResponseEntity.ok().build();
    }
}
