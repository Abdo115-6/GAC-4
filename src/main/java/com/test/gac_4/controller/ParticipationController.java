package com.test.gac_4.controller;

import com.test.gac_4.entities.Participation;
import com.test.gac_4.services.ParticipationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participations")

public class ParticipationController {

    private final ParticipationService participationService;

    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }

    // 🔹 Get all
    @GetMapping
    public List<Participation> getAll() {
        return participationService.getAll();
    }

    // 🔹 Get by id
    @GetMapping("/{id}")
    public Participation getById(@PathVariable Long id) {
        return participationService.getById(id);
    }

    // 🔹 Get by actionCharite
    @GetMapping("/action/{id}")
    public List<Participation> getByAction(@PathVariable Long id) {
        return participationService.getByActionChariteId(id);
    }

    // 🔹 Create
    @PostMapping
    public Participation create(@RequestBody Participation participation) {
        return participationService.save(participation);
    }

    // 🔹 Delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        participationService.delete(id);
    }
}