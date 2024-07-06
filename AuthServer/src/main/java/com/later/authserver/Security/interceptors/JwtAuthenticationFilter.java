package com.later.authserver.Security.interceptors;


import com.later.authserver.Exception.ApiException;
import com.later.authserver.Security.Auth.entities.LoginUser;
import com.later.authserver.Security.entities.LoginToken;
import com.later.authserver.Security.routes.entites.Route;
import com.later.authserver.Security.routes.repositories.RouteRepo;
import com.later.authserver.Security.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.time.ZoneId;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtService jwtService;
    private final RouteRepo routeRepo;
    Log log = LogFactory.getLog(getClass());

    private final UserDetailsService userDetailsService;


    @SneakyThrows
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException
            , IOException {
        if (request.getRequestURI().equals("/api/v1/auth/login")
                || request.getRequestURI().equals("/api/v1/auth/signup")
                || request.getRequestURI().startsWith("/actuator")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ApiException(403, "Access Denied");
//            filterChain.doFilter(request, response);
//            throw new BadRequestException("Bad request");
//            return;
        }

        try {
            final String jwt = authHeader.substring(7);
            final LoginToken loginToken = jwtService.getLoginTokenByToken(jwt);
            if (loginToken == null) {
                throw new ApiException(403, "Access Denied");
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null) {
                UserDetails userDetails = loginToken.getLoginUser();

                if (jwtService.isTokenValid(loginToken)) {
                    String routeBase = request.getRequestURI().replace("/api/v1/", "");
                    if (routeBase.contains("/")) {
                        routeBase = routeBase.substring(0, routeBase.indexOf("/"));
                    }
                    Route route = routeRepo.getRouteByModuleSuffix(routeBase);
                    if (route == null || !route.getActive() || (route.getAdmin() && !loginToken.getLoginUser().getAdmin() && !loginToken.getLoginUser().getSuperAdmin())) {
                        throw new NoHandlerFoundException(request.getMethod(), request.getRequestURI(), null);
                    }
                    if (route.getSuperAdmin() && !loginToken.getLoginUser().getSuperAdmin()) {
                        throw new NoHandlerFoundException(request.getMethod(), request.getRequestURI(), null);
                    }
                    jwtService.renewToken(loginToken);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    LoginUser loginUser = ((LoginUser) authToken.getPrincipal());
                    if (route.getRequiresAccess()) {
                        if (loginUser.getAuthorities().stream().noneMatch(a -> a.getAuthority().split("\\.")[0]
                                .equals(route.getModuleSuffix()))) {
                            throw new ApiException(403, "Access Denied");
                        }
                    }
                    ((LoginUser) authToken.getPrincipal()).setToken(jwt);
                    ((LoginUser) authToken.getPrincipal()).setExpiresAt(loginToken.getExpiresAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    log.info("Successfully authenticated user " + loginUser.getUsername());
                } else {
                    log.error("Authentication failed");
                    throw new ApiException(403, "Access Denied");
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            log.error("Authentication failed");
            exception.printStackTrace();
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}
