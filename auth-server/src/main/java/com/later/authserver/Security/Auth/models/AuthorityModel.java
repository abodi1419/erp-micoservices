package com.later.authserver.Security.Auth.models;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorityModel {
    private String route;
    private String systemName;
    private String moduleName;
    private Boolean active = true;
    private Boolean admin = false;
    private Boolean superAdmin = false;
    private Boolean requiresAccess = true;
    private Boolean publicAccess = false;
    private List<String> authorities;
}
