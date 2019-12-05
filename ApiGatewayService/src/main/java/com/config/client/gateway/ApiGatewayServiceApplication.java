package com.config.client.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

/**
 * API Gateway application to hit various services using Feign Client
 * tools with the help of Retry mechanism.
 * @author rjosula
 *
 */
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableRetry
public class ApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayServiceApplication.class, args);
	}

}
