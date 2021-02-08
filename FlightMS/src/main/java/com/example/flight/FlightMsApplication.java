package com.example.flight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FlightMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightMsApplication.class, args);
	}

}
