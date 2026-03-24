package com.test.gac_4.services;

import com.test.gac_4.entities.Participation;
import java.util.List;

public interface ParticipationService {

    Participation save(Participation participation);

    List<Participation> getAll();

    Participation getById(Integer id);

    Participation getById(Long id);

    List<Participation> getByActionChariteId(Integer actionId);

    List<Participation> getByActionChariteId(Long actionId);

    void delete(Integer id);

    void delete(Long id);
}