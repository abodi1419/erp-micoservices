package com.later.authserver.Security.routes.repositories;


import com.later.authserver.Security.routes.entites.RouteAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteAccessRepo extends JpaRepository<RouteAccess, Long> {
}
