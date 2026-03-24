package com.test.gac_4.services;

import com.test.gac_4.entities.Participation;
import com.test.gac_4.repositories.ParticipationRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationServiceImpl implements ParticipationService {

    private final ParticipationRepo participationRepo;

    public ParticipationServiceImpl(ParticipationRepo participationRepo) {
        this.participationRepo = participationRepo;
    }

    @Override
    public Participation save(Participation participation) {
        return participationRepo.save(participation);
    }

    @Override
    public List<Participation> getAll() {
        return participationRepo.findAll();
    }

    @Override
    public Participation getById(Integer id) {
        return null;
    }

    @Override
    public Participation getById(Long id) {
        return participationRepo.findById(id).orElse(null);
    }

    @Override
    public List<Participation> getByActionChariteId(Integer actionId) {
        return List.of();
    }

    @Override
    public List<Participation> getByActionChariteId(Long actionId) {
        return participationRepo.findByActionCharite_Id(actionId); // 🔥 مهم
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void delete(Long id) {
        participationRepo.deleteById(id);
    }
}