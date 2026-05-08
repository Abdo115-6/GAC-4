package com.test.gac_4.repositories;

import com.test.gac_4.entities.ActionCharite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActionChariteRepo extends JpaRepository<ActionCharite, Long> {
    List<ActionCharite> findByOrganisationId(Long organisationId);
    List<ActionCharite> findByCategory(String category);
    List<ActionCharite> findByArchivedFalse();
    List<ActionCharite> findByOrganisationIdAndArchivedFalse(Long organisationId);
}