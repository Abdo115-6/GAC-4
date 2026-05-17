package com.test.gac_4.repositories;

import com.test.gac_4.entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipationRepo extends JpaRepository<Participation, Long> {
    List<Participation> findByActionId(Long actionId);
    List<Participation> findByUserId(Long userId);
    Optional<Participation> findByUserIdAndActionId(Long userId, Long actionId);
    Long countByActionId(Long actionId);
}