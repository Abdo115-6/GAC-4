package com.test.gac_4.repositories;

import com.test.gac_4.entities.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrganisationRepo extends JpaRepository<Organisation, Long> {
    Optional<Organisation> findByEmail(String email);
    Optional<Organisation> findByNom(String nom);
    List<Organisation> findByValideTrue();
    List<Organisation> findByValideFalse();
    List<Organisation> findByAdminId(Long adminId);
    boolean existsByEmail(String email);
}