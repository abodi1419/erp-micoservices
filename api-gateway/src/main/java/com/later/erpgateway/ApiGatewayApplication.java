package com.later.erpgateway;

import com.later.erpgateway.filters.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.config.GatewayAutoConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@ImportAutoConfiguration({GatewayAutoConfiguration.class})
@EnableFeignClients

public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, AuthFilter authFilter) {
        return builder.routes()
                .route("authService", r -> r.path("/api/v1/auth/**", "/api/v1/routes/**")
                        .uri("lb://auth-service")
                )
                .route("procurement-po", r -> r.path("/api/v1/procurement/**", "/api/v1/purchase-order/**", "/api/v1/purchase-request/**")
                        .filters(f -> f.filter(authFilter)).uri("lb://procurement-service")
                )
                .route("warehouse", r -> r.path("/api/v1/warehouse/**", "/api/v1/warehouse-detail/**", "/api/v1/stock/**")
                        .filters(f -> f.filter(authFilter)).uri("lb://inventory-service")
                )
                .route("supplier", r -> r.path("/api/v1/supplier/**")
                        .filters(f -> f.filter(authFilter)).uri("lb://supplier-service")
                )
                .route("item-category", r -> r.path("/api/v1/item-category/**", "/api/v1/item/**", "/api/v1/measure-unit/**")
                        .filters(f -> f.filter(authFilter)).uri("lb://item-service")
                )
                .route("company", r -> r.path("/api/v1/company/**", "/api/v1/employees/**", "/api/v1/job-titles/**")
                        .filters(f -> f.filter(authFilter)).uri("lb://company-service")
                ).build();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
