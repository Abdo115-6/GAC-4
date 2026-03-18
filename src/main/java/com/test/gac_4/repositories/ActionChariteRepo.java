package com.test.gac_4.repositories;

import com.test.gac_4.entities.ActionCharite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActionChariteRepo extends JpaRepository<ActionCharite, Integer> {
    List<ActionCharite> findByOrganisationId(Integer organisationId);
}