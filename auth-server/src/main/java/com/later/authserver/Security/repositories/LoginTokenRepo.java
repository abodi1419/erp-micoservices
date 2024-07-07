package com.later.authserver.Security.repositories;


import com.later.authserver.Security.entities.LoginToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginTokenRepo extends JpaRepository<LoginToken, Long> {

    @Transactional
    @Modifying
    @Query("update LoginToken lt set lt.valid=false where lt.loginUser.id=:loginUserId ")
    void disableTokens(@Param("loginUserId") Long loginUserId);

    LoginToken findByToken(String token);
}
