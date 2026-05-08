package com.test.gac_4.controller;

import com.test.gac_4.dto.DonDTO;
import com.test.gac_4.services.DonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/donations")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class DonationController {
    @Autowired
    private DonService donService;

    @PostMapping
    public ResponseEntity<DonDTO> createDonation(@Valid @RequestBody DonDTO dto) {
        DonDTO created = donService.createDon(dto);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonDTO> getDonation(@PathVariable Long id) {
        DonDTO don = donService.getDonById(id);
        return ResponseEntity.ok(don);
    }

    @PostMapping("/{id}/confirm")
    public ResponseEntity<DonDTO> confirmDonation(@PathVariable Long id) {
        DonDTO confirmed = donService.confirmDon(id);
        return ResponseEntity.ok(confirmed);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DonDTO>> getUserDonations(@PathVariable Long userId) {
        List<DonDTO> donations = donService.getDonationsByUser(userId);
        return ResponseEntity.ok(donations);
    }

    @GetMapping("/action/{actionId}")
    public ResponseEntity<List<DonDTO>> getActionDonations(@PathVariable Long actionId) {
        List<DonDTO> donations = donService.getDonationsByAction(actionId);
        return ResponseEntity.ok(donations);
    }

    @GetMapping("/action/{actionId}/total")
    public ResponseEntity<Double> getTotalDonationsByAction(@PathVariable Long actionId) {
        Double total = donService.getTotalDonationsByAction(actionId);
        return ResponseEntity.ok(total);
    }
}
