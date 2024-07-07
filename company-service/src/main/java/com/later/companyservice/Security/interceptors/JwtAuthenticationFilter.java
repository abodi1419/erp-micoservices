package com.later.companyservice.Security.interceptors;


import com.later.companyservice.Exception.ApiException;
import com.later.companyservice.Security.Auth.entities.Authority;
import com.later.companyservice.Security.Auth.entities.Employee;
import com.later.companyservice.Security.Auth.entities.LoginUser;
import com.later.companyservice.Security.services.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtService jwtService;


    @SneakyThrows
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException
            , IOException {
        final String authHeader = request.getHeader("Authorization");
        if(request.getRequestURI().startsWith("/actuator")){
            filterChain.doFilter(request, response);
            return;
        }
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ApiException(403, "Access Denied");
        }

        try {
            final String jwt = authHeader.substring(7);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Claims claims = jwtService.extractAllClaim(jwt);
            LoginUser loginUser = new LoginUser();
            Employee employee = new Employee();
            loginUser.setUsername((String) claims.get("username"));
            loginUser.setEmail((String) claims.get("email"));
            loginUser.setAdmin((Boolean) claims.getOrDefault("admin",false));
            loginUser.setSuperAdmin((Boolean) claims.getOrDefault("superAdmin",false));
            List<String> authorities = (List<String>) claims.getOrDefault("authorities",List.of());
            List<Authority> authorities1 = new ArrayList<>();
            for (String authority : authorities) {
                authorities1.add(new Authority(authority));
            }
            loginUser.setAuthorities(authorities1);
            employee.setId(((Integer)claims.get("employeeId")).longValue());
            employee.setEmployeeFullName((String) claims.get("employeeFullName"));
            employee.setEmployeeFullNameAr((String) claims.get("employeeFullNameAr"));
            employee.setEmployeeShortName((String) claims.get("employeeShortName"));
            employee.setEmployeeShortNameAr((String) claims.get("employeeShortNameAr"));
            employee.setEmployeeCompanyNumber((String) claims.get("employeeCompanyNumber"));
            employee.setEmployeeHireDate((LocalDate) claims.get("employeeHireDate"));
            employee.setEmployeeEmail((String) claims.get("employeeEmail"));
            loginUser.setEmployee(employee);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    loginUser,
                    null,
                    loginUser.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);
            ((LoginUser) authToken.getPrincipal()).setToken(jwt);
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            exception.printStackTrace();
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}
