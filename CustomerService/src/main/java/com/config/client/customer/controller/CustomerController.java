package com.config.client.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.pojos.CustomerPayload;
import com.config.client.customer.service.CustomerService;

/**
 * Handler class to manage the customer data
 * @author rjosula
 *
 */
@RestController
@RequestMapping("customer/service")
@RefreshScope
public class CustomerController {
	
	@Value("${greetings.message}")
	private String msg;
	
	@RequestMapping("/msg")
	public String getCustMsg() {
		return msg;
	}
	
	@Autowired
	private CustomerService customerService;
	
	/**
	 * Handler to save a customer information
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public ResponseEntity<?> saveCustomers(@RequestBody CustomerPayload customer) {
		CustomerPayload customerPayload = customerService.saveCustomer(customer);
		return new ResponseEntity<>(customerPayload, HttpStatus.OK);
	}
	
	@GetMapping("/findall")
	public ResponseEntity<?> getAllCustomers(){
		return new ResponseEntity<>(customerService.findAllCustomers(), HttpStatus.OK);
	}
}
