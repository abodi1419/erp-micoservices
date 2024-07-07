package com.later.authserver.Security.Auth.repositories;


import com.later.authserver.Security.Auth.entities.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginUserRepo extends JpaRepository<LoginUser, Long> {

    LoginUser getLoginUserByEmail(String email);

    @Query("select l from LoginUser l where l.username=:username or l.email=:username")
    Optional<LoginUser> findByEmailOrUsername(String username);

    LoginUser getLoginUserByUsername(String username);
}
