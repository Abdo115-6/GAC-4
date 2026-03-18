package com.test.gac_4.repositories;

import com.test.gac_4.entities.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface usersrepo extends JpaRepository<users,Integer> {
}
