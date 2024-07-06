package com.later.erpgateway.filters;

import com.later.erpgateway.Exception.ApiException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class AuthFilter implements GatewayFilter, Ordered {
    @Autowired
    private WebClient.Builder webClientBuilder;
    Log log = LogFactory.getLog(getClass());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (exchange.getRequest().getURI().getPath().startsWith("/actuator")){
            log.info("Actuator: Allowed");

            return chain.filter(exchange);
        }
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/api/v1/auth/check")
                .header("Authorization", exchange.getRequest().getHeaders().getFirst("Authorization"))
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess((response) -> {
                    log.info("Auth Filter: Authentication Successful");
                }).then(Mono.defer(() -> chain.filter(exchange)))
                .onErrorMap(e->{
                    log.error("Auth Filter: Authentication Failed");
                    return new ApiException(403, "Access denied");
                });

    }



    @Override
    public int getOrder() {
        return 0;
    }
}
