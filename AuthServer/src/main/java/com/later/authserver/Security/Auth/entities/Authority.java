package com.later.authserver.Security.Auth.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.authserver.Security.routes.entites.Route;
import com.later.authserver.Security.routes.entites.RouteAccess;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "sec_user_authorities")
@Getter
@Setter
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "login_user_id")
    @JsonIgnore
    private LoginUser loginUser;
    @ManyToOne
    private Route route;
    @ManyToOne
    @JsonIgnore
    private RouteAccess routeAccess;

    @Override
    public String getAuthority() {
        return route.getModuleSuffix() + "." + routeAccess.getAccess();
    }


}
