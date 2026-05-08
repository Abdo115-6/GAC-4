package com.test.gac_4.repositories;

import com.test.gac_4.entities.Don;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DonRepo extends JpaRepository<Don, Long> {
    List<Don> findByUserId(Long userId);
    List<Don> findByActionId(Long actionId);
    List<Don> findByUserIdAndActionId(Long userId, Long actionId);
    
    @Query("SELECT SUM(d.montant) FROM Don d WHERE d.action.id = ?1 AND d.status = 'CONFIRMED'")
    Double sumDonationsByActionId(Long actionId);
}