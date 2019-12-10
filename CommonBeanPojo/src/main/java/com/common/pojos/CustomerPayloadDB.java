package com.common.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO to carry the customer information
 * @author rjosula
 *
 */
@JsonInclude
public class CustomerPayloadDB {

	@JsonProperty(value = "user_id")
	private Integer user_id;
	
	@JsonProperty(value = "user_name")
	private String user_name;
	
	@JsonProperty(value = "first_name")
	private String first_name;

	@JsonProperty(value = "last_name")
	private String last_name;
	
	@JsonProperty(value = "password")
	private String password;

	public Integer getUserId() {
		return user_id;
	}

	public void setUserId(Integer userId) {
		this.user_id = userId;
	}

	public String getUserName() {
		return user_name;
	}

	public void setUserName(String userName) {
		this.user_name = userName;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String firstName) {
		this.first_name = firstName;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String lastName) {
		this.last_name = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "CustomerPayload [userId=" + user_id + ", userName=" + user_name + ", firstName=" + first_name
				+ ", lastName=" + last_name + ", password=" + password + "]";
	}
	
}
