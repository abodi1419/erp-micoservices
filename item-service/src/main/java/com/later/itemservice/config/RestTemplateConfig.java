package com.later.itemservice.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.additionalInterceptors(new AuthInterceptor())
                .setConnectTimeout(Duration.ofSeconds(5))
                .setConnectTimeout(Duration.ofSeconds(5))
                .errorHandler(new RestErrorHandler(new ObjectMapper()))
                .build();
    }
}
