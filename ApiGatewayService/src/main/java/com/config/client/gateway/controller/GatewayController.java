package com.config.client.gateway.controller;

import java.net.ConnectException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.pojos.CustomerPayload;
import com.config.client.gateway.service.GatewayControllerService;

/**
 * Handler for Gateway application to call the respective micro service.
 * 
 * @author rjosula
 *
 */
@RestController
@RequestMapping("/gateway/api")
@RefreshScope
public class GatewayController {

	@Autowired
	public GatewayControllerService gateWayService;

	@Value("${msg.gateway}")
	private String msg;

	@RequestMapping(value = "/msg")
	public String getMessageFromConfig() {
		return msg;
	}

	@RequestMapping("/")
	public String defaultUrl() {
		return "helloApiGee";
	}

	/**
	 * To get all the customers from customer service using Feign Client.
	 * @return
	 */
	@GetMapping("/allCustomers")
	public ResponseEntity<?> getAllCustomers(){
		ResponseEntity<?> responseE = null;
		try {
			responseE = gateWayService.customerService(true, true);
		} catch (ConnectException e) {
			e.printStackTrace();
		}
		return responseE;
	}


	/**
	 * Post request for saving the customer information
	 * 
	 * @param customerPayload
	 * @return
	 */
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public ResponseEntity<?> saveAllCustomers(@RequestBody CustomerPayload customerPayload) { 
		return gateWayService.saveCustomer(customerPayload, true, true); 
	}

}
