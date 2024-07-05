package com.later.serviceregisteryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegisteryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRegisteryServerApplication.class, args);
    }

}
