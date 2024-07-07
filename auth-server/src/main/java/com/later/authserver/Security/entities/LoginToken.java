package com.later.authserver.Security.entities;


import com.later.authserver.Security.Auth.entities.LoginUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sec_login_tokens")
@Getter
@Setter
public class LoginToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "text")
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "login_user_id")
    private LoginUser loginUser;
    private Boolean valid;

}
