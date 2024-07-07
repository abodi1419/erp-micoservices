package com.later.authserver.Security.Auth.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.authserver.Security.entities.LoginToken;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sec_login_user")
@Getter
@Setter
public class LoginUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    @JsonIgnore
    private String password;

    private Boolean resetFlag;

    private String resetPassword;

    @OneToOne()
    @JoinColumn(name = "employee_id", referencedColumnName = "employeeId")
    private Employee employee;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column(columnDefinition = "bit default 1")
    private Boolean isNonExpired;
    @Column(columnDefinition = "bit default 1")
    private Boolean isNonLocked;
    @Column(columnDefinition = "bit default 1")
    private Boolean enabled;
    @Column(columnDefinition = "boolean default false")
    private Boolean admin;

    @OneToMany(mappedBy = "loginUser", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<LoginToken> loginTokens;

    @OneToMany(mappedBy = "loginUser", fetch = FetchType.EAGER)
    private List<Authority> authorities;

    @Transient
    private String token;
    @Transient
    private Long expiresAt;
    @Column(columnDefinition = "bit default 0")
    private Boolean superAdmin = false;


}
