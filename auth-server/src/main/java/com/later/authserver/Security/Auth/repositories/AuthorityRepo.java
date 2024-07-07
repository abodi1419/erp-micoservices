package com.later.authserver.Security.Auth.repositories;

import com.later.authserver.Security.Auth.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, Long> {
}
