package com.later.authserver.App.routes.service;

import com.later.authserver.Security.Auth.repositories.AuthorityRepo;
import com.later.authserver.Security.Auth.repositories.LoginUserRepo;
import com.later.authserver.Security.routes.entites.Route;
import com.later.authserver.Security.routes.entites.RouteAccess;
import com.later.authserver.App.routes.model.AccessCreationModel;
import com.later.authserver.App.routes.model.RouteCreationModel;
import com.later.authserver.Security.Auth.entities.Authority;
import com.later.authserver.Security.Auth.entities.Employee;
import com.later.authserver.Security.Auth.entities.LoginUser;
import com.later.authserver.Exception.ApiException;
import com.later.authserver.Security.routes.repositories.RouteAccessRepo;
import com.later.authserver.Security.routes.repositories.RouteRepo;
import com.later.authserver.constants.ResponseText;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final AuthorityRepo authorityRepo;
    private final RouteRepo routeRepo;
    private final LoginUserRepo loginUserRepo;
    private final RouteAccessRepo routeAccessRepo;

    public String create(RouteCreationModel routeCreationModel, Employee loginUser) throws ApiException {
        Route route = validateCreationModel(routeCreationModel, loginUser);
        List<Authority> grantedAuthorities = grantAuthorities(route, routeCreationModel, loginUser);
        routeRepo.save(route);
        if (!grantedAuthorities.isEmpty()) {
            authorityRepo.saveAll(grantedAuthorities);
        }
        return ResponseText.CREATED.text();
    }

    private Route validateCreationModel(RouteCreationModel routeCreationModel, Employee loginUser) throws ApiException {
        Route route = new Route();
        if (routeCreationModel.getActive() == null) {
            routeCreationModel.setActive(true);
        }
        if (routeCreationModel.getPublicAccess() == null) {
            routeCreationModel.setPublicAccess(false);
        }
        if (routeCreationModel.getRequiresAccess() == null) {
            routeCreationModel.setRequiresAccess(true);
        }
        if (routeCreationModel.getAdmin() == null) {
            routeCreationModel.setAdmin(false);
        }
        route.setActive(routeCreationModel.getActive());
        route.setRoute(routeCreationModel.getRoute());
        route.setAdmin(routeCreationModel.getAdmin());
        route.setRequiresAccess(routeCreationModel.getRequiresAccess());
        route.setPublicAccess(routeCreationModel.getPublicAccess());
        route.setModuleCode(routeCreationModel.getModuleCode());
        route.setModuleName(routeCreationModel.getModuleName());
        route.setModuleSuffix(routeCreationModel.getModuleSuffix());


        return route;
    }

    private List<Authority> grantAuthorities(Route route, RouteCreationModel routeCreationModel, Employee loginUser) throws ApiException {
        List<Authority> authorities = new ArrayList<>();
        if (!routeCreationModel.getAccessCreationModelList().isEmpty()) {
            for (AccessCreationModel a : routeCreationModel.getAccessCreationModelList()) {

                LoginUser loginUser1 = loginUserRepo.findById(a.getLoginUserId()).orElse(null);
                if (loginUser1 == null) {
                    throw new ApiException(404, "Login user not found");
                }
                List<RouteAccess> routeAccesses = routeAccessRepo.findAllById(a.getAccesses());
                for (RouteAccess r : routeAccesses) {
                    Authority authority = new Authority();
//                    authority.setRoute(route);
//                    authority.setRouteAccess(r);
//                    authority.setLoginUser(loginUser1);
                    authorities.add(authority);
                }
            }
        }
        return authorities;
    }

}
