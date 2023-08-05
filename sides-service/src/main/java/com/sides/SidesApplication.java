package com.sides;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SidesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SidesApplication.class, args);
	}

}
