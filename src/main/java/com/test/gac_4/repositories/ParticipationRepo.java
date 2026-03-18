package com.test.gac_4.repositories;

import com.test.gac_4.entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ParticipationRepo extends JpaRepository<Participation, Integer> {
    List<Participation> findByUserId(Integer userId);
    List<Participation> findByActionChariteId(Integer actionId);
}