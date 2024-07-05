package com.later.authserver.Security.Auth.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.authserver.Security.Auth.entities.Authority;
import com.later.authserver.Security.Auth.entities.Employee;
import com.later.authserver.Security.entities.LoginToken;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class LoginUserModel {

    private Long id;
    private String username;
    private String email;
    private Boolean resetFlag;
    private Employee employee;

    private Boolean isNonExpired;
    private Boolean isNonLocked;
    private Boolean admin;
    private List<AuthorityModel> authorities;
    private Boolean superAdmin;
}
