package com.config.client.gateway.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.common.pojos.CustomerPayload;

/**
 * Proxy interface for the Gateway of Customers services to hit the customer service related services.
 * @author rjosula
 *
 */
/**
 * Proxy interface defined for all the Customer related URL's which is
 * configured and individual methods shall be targetted to get the proper
 * response.
 * 
 * @author rjosula
 *
 */
@FeignClient(name = "gateway-client", url = "${customer.gateway.client.url}")
public interface CustomerGatewayApp {

	/**
	 * Feign client to hit the server to get all the customers list.
	 * @return
	 */
	@GetMapping(value="/findall", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllCustomers();
	
	/**
	 * Feign client to save a customer information
	 * @param customer
	 * @return
	 */
	@PostMapping(value = "/addCustomer")
	public ResponseEntity<?> saveCustomer(@RequestBody CustomerPayload customer);
}
