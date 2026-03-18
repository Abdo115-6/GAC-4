package com.test.gac_4.repositories;

import com.test.gac_4.entities.Don;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DonRepo extends JpaRepository<Don, Integer> {
    List<Don> findByUserId(Integer userId);
    List<Don> findByActionChariteId(Integer actionId);
}