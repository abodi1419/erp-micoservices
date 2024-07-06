package com.later.supplierservice.Security.Auth.entities;


import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


@Setter
public class Authority implements GrantedAuthority {
    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    public Authority() {
    }

    @Override
    public String getAuthority() {
        return authority;
    }


}
