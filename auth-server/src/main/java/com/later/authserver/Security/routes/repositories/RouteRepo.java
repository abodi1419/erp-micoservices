package com.later.authserver.Security.routes.repositories;



import com.later.authserver.Security.routes.entites.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepo extends JpaRepository<Route, Long> {
    Route getRouteByRoute(String route);

    Route getRouteByModuleSuffix(String moduleSuffix);
}
