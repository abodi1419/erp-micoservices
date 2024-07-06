package com.later.erp.App.superAdmin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteService {
//    private final AuthorityRepo authorityRepo;

//    public String create(RouteCreationModel routeCreationModel, Employee loginUser) throws ApiException {
//        Route route = validateCreationModel(routeCreationModel, loginUser);
//        List<Authority> grantedAuthorities = grantAuthorities(route, routeCreationModel, loginUser);
//        routeRepo.save(route);
//        if (!grantedAuthorities.isEmpty()) {
//            authorityRepo.saveAll(grantedAuthorities);
//        }
//        return ResponseText.CREATED.text();
//    }
//
//    private Route validateCreationModel(RouteCreationModel routeCreationModel, Employee loginUser) throws ApiException {
//        Route route = new Route();
//        if (routeCreationModel.getActive() == null) {
//            routeCreationModel.setActive(true);
//        }
//        if (routeCreationModel.getPublicAccess() == null) {
//            routeCreationModel.setPublicAccess(false);
//        }
//        if (routeCreationModel.getRequiresAccess() == null) {
//            routeCreationModel.setRequiresAccess(true);
//        }
//        if (routeCreationModel.getAdmin() == null) {
//            routeCreationModel.setAdmin(false);
//        }
//        route.setActive(routeCreationModel.getActive());
//        route.setRoute(routeCreationModel.getRoute());
//        route.setAdmin(routeCreationModel.getAdmin());
//        route.setRequiresAccess(routeCreationModel.getRequiresAccess());
//        route.setPublicAccess(routeCreationModel.getPublicAccess());
//        route.setModuleCode(routeCreationModel.getModuleCode());
//        route.setModuleName(routeCreationModel.getModuleName());
//        route.setModuleSuffix(routeCreationModel.getModuleSuffix());
//
//
//        return route;
//    }
//
//    private List<Authority> grantAuthorities(Route route, RouteCreationModel routeCreationModel, Employee loginUser) throws ApiException {
//        List<Authority> authorities = new ArrayList<>();
//        if (!routeCreationModel.getAccessCreationModelList().isEmpty()) {
//            for (AccessCreationModel a : routeCreationModel.getAccessCreationModelList()) {
//
//                LoginUser loginUser1 = loginUserRepo.findById(a.getLoginUserId()).orElse(null);
//                if (loginUser1 == null) {
//                    throw new ApiException(404, "Login user not found");
//                }
//                List<RouteAccess> routeAccesses = routeAccessRepo.findAllById(a.getAccesses());
//                for (RouteAccess r : routeAccesses) {
//                    Authority authority = new Authority();
////                    authority.setRoute(route);
////                    authority.setRouteAccess(r);
////                    authority.setLoginUser(loginUser1);
//                    authorities.add(authority);
//                }
//            }
//        }
//        return authorities;
//    }

}
