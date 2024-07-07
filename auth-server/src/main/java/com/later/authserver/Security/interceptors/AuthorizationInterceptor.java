//package com.later.authserver.Security.interceptors;
//
//
//import com.later.authserver.Authorization.Security.Auth.entities.LoginUser;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//@Component
//public class AuthorizationInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//
//            // Check if the method has the @PreAuthorize annotation
//            PreAuthorize preAuthorize = handlerMethod.getMethodAnnotation(PreAuthorize.class);
//            if (preAuthorize != null) {
//                // Extract the authorization expression from the annotation
//                String expression = preAuthorize.value();
//
//                // Evaluate the authorization expression
//                // Implement your own authorization logic here
//                if (expression.startsWith("hasAuthority")) {
//                    if (!evaluateAuthorizationExpression(expression.replace("hasAuthority('", "").replace("')", ""))) {
//                        throw new AccessDeniedException("Access denied");
//                    }
//                }
//
//
//            }
//        }
//
//        return true;
//    }
//
//
//    private boolean evaluateAuthorizationExpression(String expression) {
//        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        final StringBuilder exp = new StringBuilder(expression.replaceAll("'",""));
//        return loginUser.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(expression));
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        if (ex != null) {
//            ex.printStackTrace();
//            throw ex;
//        }
//    }
//}