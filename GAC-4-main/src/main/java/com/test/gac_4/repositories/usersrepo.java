package com.test.gac_4.repositories;

import com.test.gac_4.entities.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface usersrepo extends JpaRepository<users, Long> {
    Optional<users> findByEmail(String email);
    boolean existsByEmail(String email);
    List<users> findByIsAdmin(Boolean isAdmin);
}
