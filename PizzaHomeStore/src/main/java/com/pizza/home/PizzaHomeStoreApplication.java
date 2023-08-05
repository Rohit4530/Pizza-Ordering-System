package com.pizza.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableDiscoveryClient
public class PizzaHomeStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaHomeStoreApplication.class, args);
	}

}
