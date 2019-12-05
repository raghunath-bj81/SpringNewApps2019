package com.config.client.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.pojos.CustomerPayload;
import com.config.client.customer.model.Customer;
import com.config.client.customer.repository.CustomerRepository;

import org.apache.log4j.Logger;

/**
 * Customer service implementation component
 * @author rjosula
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	private Logger logger = Logger.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	/**
	 * To save customer
	 * @param CustomerPayload
	 * @return CustomerPayload
	 */
	@Override
	public CustomerPayload saveCustomer(CustomerPayload customer) {
		Customer newCustomer = new Customer();
		BeanUtils.copyProperties(customer, newCustomer);
		Customer customerSaved = customerRepository.save(newCustomer);
		CustomerPayload payload = new CustomerPayload();
		BeanUtils.copyProperties(customerSaved, payload);
		if(null != payload && null != payload.getFirstName()) {
			logger.info("customerPayload is "+payload.toString());
		} else {
			logger.info("Copy of source and target object is failed");
		}
		return payload;
	}

	/**
	 * Find all customer payloads
	 * @return customerPayloads
	 */
	@Override
	public List<CustomerPayload> findAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		List<CustomerPayload> customerPayloads = new ArrayList<CustomerPayload>();
		customers.forEach(customer -> {
			CustomerPayload payload = new CustomerPayload();
			BeanUtils.copyProperties(customer, payload);
			customerPayloads.add(payload);
		});
		return customerPayloads;
	}

	/**
	 * Finds the customer by customer id
	 */
	@Override
	public CustomerPayload findCustomerById(Long customerId) {
		CustomerPayload custPayload = new CustomerPayload();
		Optional<Customer> customerOpt = customerRepository.findById(customerId.intValue());
		if(customerOpt.isPresent()) {
			BeanUtils.copyProperties(customerOpt.get(), custPayload);
		}
		logger.debug(custPayload.toString());
		return custPayload;
	}

	@Override
	public CustomerPayload updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

}
