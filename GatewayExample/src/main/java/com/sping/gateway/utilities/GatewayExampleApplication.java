package com.sping.gateway.utilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
@ImportResource("application-gateway-context.xml")
public class GatewayExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayExampleApplication.class, args);
	}

}
