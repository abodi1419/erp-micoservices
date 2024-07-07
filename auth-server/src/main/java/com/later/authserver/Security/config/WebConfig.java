package com.later.authserver.Security.config;//package com.later.authserver.Authorization.Security.config;
//
//import com.later.authserver.Authorization.Security.interceptors.AuthorizationInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//@Configuration
//public class WebConfig extends WebMvcConfigurationSupport {
//
//    @Autowired
//    private AuthorizationInterceptor authorizationInterceptor;
//
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/api/v1/**");
//    }
//}
