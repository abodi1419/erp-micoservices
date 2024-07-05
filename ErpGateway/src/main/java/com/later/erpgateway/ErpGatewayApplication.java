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

public class ErpGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErpGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, AuthFilter authFilter) {
        return builder.routes()
                .route("authService", r -> r.path("/api/v1/auth/**")
                        .uri("lb://authService")
                ).route("procurement-po", r -> r.path("/api/v1/purchase-order/**")
                        .filters(f -> f.filter(authFilter)).uri("lb://procurementService")
                ).route("procurement-pr", r -> r.path("/api/v1/purchase-request/**")
                        .filters(f -> f.filter(authFilter)).uri("lb://procurementService")
                ).route("warehouse", r -> r.path("/api/v1/warehouse/**")
                        .filters(f -> f.filter(authFilter)).uri("lb://inventoryService")
                ).route("stock", r -> r.path("/api/v1/stock/**")
                        .filters(f -> f.filter(authFilter)).uri("lb://inventoryService")
                ).route("warehouse-details", r -> r.path("/api/v1/warehouse-detail/**")
                        .filters(f -> f.filter(authFilter)).uri("lb://inventoryService")
                )
                .route("erp", r -> r.path("/api/v1/**")
                        .filters(f -> f.filter(authFilter)).uri("lb://erp")
                )
                .build();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
