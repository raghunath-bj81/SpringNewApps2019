package com.config.client.customer.service;

import java.util.List;

import com.common.pojos.CustomerPayload;
import com.config.client.customer.model.Customer;

/**
 * Customer interface to manage customer operations.
 * @author rjosula
 *
 */
public interface CustomerService {

	public CustomerPayload saveCustomer(CustomerPayload customer);
	public List<CustomerPayload> findAllCustomers();
	public CustomerPayload findCustomerById(Long customerId);
	public CustomerPayload updateCustomer(Customer customer);
	
}
