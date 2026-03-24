package com.test.gac_4.repositories;

import com.test.gac_4.entities.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrganisationRepo extends JpaRepository<Organisation, Long> {

}