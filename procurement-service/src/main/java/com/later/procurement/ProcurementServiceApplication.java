package com.later.procurement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableDiscoveryClient
@FeignClient(value = "procurementService", name = "procurementService")
public class ProcurementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcurementServiceApplication.class, args);
	}

}
