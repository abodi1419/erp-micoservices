package com.later.authserver.Security.Auth.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.authserver.Security.Auth.entities.LoginUser;
import com.later.authserver.Security.routes.entites.Route;
import com.later.authserver.Security.routes.entites.RouteAccess;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@Setter
public class AuthorityModel {
    private String route;
    private String moduleName;
    private Boolean active = true;
    private Boolean admin = false;
    private Boolean superAdmin = false;
    private Boolean requiresAccess = true;
    private Boolean publicAccess = false;
    private List<String> authorities;


}
