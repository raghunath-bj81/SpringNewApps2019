package com.kafka.consumer.example.service;

import java.util.List;

import com.kafka.consumer.example.model.UserInfo;

public interface CustomerService {

	public UserInfo saveUserInfo(UserInfo userInfo);
	public List<UserInfo> findAllUsers();
}
