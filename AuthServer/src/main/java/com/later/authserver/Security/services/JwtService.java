package com.later.authserver.Security.services;


import com.later.authserver.Security.Auth.entities.Authority;
import com.later.authserver.Security.Auth.entities.LoginUser;
import com.later.authserver.Security.entities.LoginToken;
import com.later.authserver.Security.repositories.LoginTokenRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final Logger log = LoggerFactory.getLogger(JwtService.class);
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${security.jwt.multiple-instances}")
    private boolean allowMultipleInstance;
    @Value("${security.jwt.renews}")
    private boolean renews;
    private final LoginTokenRepo loginTokenRepo;

    public JwtService(LoginTokenRepo loginTokenRepo) {
        this.loginTokenRepo = loginTokenRepo;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public LoginToken generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        LoginUser loginUser = (LoginUser) userDetails;
        claims.put("username", loginUser.getUsername());
        claims.put("email", loginUser.getEmail());
        if (loginUser.getAdmin()) {
            claims.put("admin", true);
        }
        if (loginUser.getSuperAdmin()) {
            claims.put("superAdmin", true);
        }
        claims.put("authorities", loginUser.getAuthorities().stream().map(Authority::getAuthority).toList());
        claims.put("employeeId", loginUser.getEmployee().getId());
        claims.put("employeeFullName", loginUser.getEmployee().getEmployeeFullName());
        claims.put("employeeFullNameAr", loginUser.getEmployee().getEmployeeFullNameAr());
        claims.put("employeeShortName", loginUser.getEmployee().getEmployeeShortName());
        claims.put("employeeShortNameAr", loginUser.getEmployee().getEmployeeShortNameAr());
        claims.put("employeeCompanyNumber", loginUser.getEmployee().getEmployeeCompanyNumber());
        claims.put("employeeEmail", loginUser.getEmployee().getEmployeeEmail());
        claims.put("employeeHireDate", loginUser.getEmployee().getEmployeeHireDate());
        String token = generateToken(claims, userDetails);

        LoginToken loginToken = new LoginToken();
        loginToken.setToken(token);
        loginToken.setCreatedAt(LocalDateTime.now());
        loginToken.setLoginUser((LoginUser) userDetails);
        loginToken.setExpiresAt(LocalDateTime.now().plusSeconds(jwtExpiration / 1000));
        loginToken.setValid(true);
        if (!allowMultipleInstance) {
            loginTokenRepo.disableTokens(((LoginUser) userDetails).getId());
        }

        loginTokenRepo.save(loginToken);

        return loginToken;
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public long getExpirationTime() {
        return jwtExpiration;
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(LoginToken loginToken) {
//        final String username = extractUsername(token);
        return !isTokenExpired(loginToken) && loginToken.getValid();
    }

    private boolean isTokenExpired(LoginToken loginToken) {
        return loginToken.getExpiresAt().isBefore(LocalDateTime.now());
    }

//    private LocalDateTime extractExpiration(String token) {
////        loginToken.getEndAt().isBefore(LocalDateTime.now());
//        return loginToken.getEndAt();
//    }

    public LoginToken getLoginTokenByToken(String token) {
        return loginTokenRepo.findByToken(token);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public void renewToken(LoginToken loginToken) {
        if (renews) {
            loginToken.setExpiresAt(LocalDateTime.now().plusSeconds(jwtExpiration / 1000));
            loginTokenRepo.save(loginToken);
        }
    }
}
