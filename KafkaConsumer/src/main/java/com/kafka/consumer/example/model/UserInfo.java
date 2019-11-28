package com.kafka.consumer.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * Model to store the User information into user table.
 * @author rjosula
 *
 */
@Document(collection = "UserInfo")
@Data
public class UserInfo {

	@Id
	public Integer userId;
	public String name;
	public String dept;
	
	@Transient
    public static final String SEQUENCE_NAME = "_id";

	public UserInfo(Integer userId, String name, String dept) {
		this.userId = userId;
		this.name = name;
		this.dept = dept;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}


}
