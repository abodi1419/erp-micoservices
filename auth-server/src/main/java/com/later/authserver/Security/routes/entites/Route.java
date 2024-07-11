package com.later.authserver.Security.routes.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sec_routes")
@Getter
@Setter
public class Route {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    private Long id;
    @Column(unique = true)
    private String route;
    private String moduleName;
    private Boolean active = true;
    private Boolean admin = false;
    @Column(columnDefinition = "bit default 0")
    private Boolean superAdmin = false;
    @Column(columnDefinition = "bit default 1")
    private Boolean requiresAccess = true;
    @Column(columnDefinition = "bit default 0")
    private Boolean publicAccess = false;
    @Column(unique = true)
    @JsonIgnore
    private String moduleCode;
    @Column(unique = true)
    @JsonIgnore
    private String moduleSuffix;
    private String systemName;


}
