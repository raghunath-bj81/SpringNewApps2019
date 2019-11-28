package com.kafka.consumer.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafka.consumer.example.dao.CustomerDAO;
import com.kafka.consumer.example.model.UserInfo;

/**
 * Customer Service Implementation to save or get the user information.
 * @author rjosula
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	/**
	 * To save the User Info
	 */
	@Override
	public UserInfo saveUserInfo(UserInfo userInfo) {
		return customerDAO.save(userInfo);
	}

	@Override
	public List<UserInfo> findAllUsers() {
		return (List<UserInfo>) customerDAO.findAll();
	}
}
