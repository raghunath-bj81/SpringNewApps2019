package com.kafka.consumer.example.util;

import java.util.Comparator;

import com.kafka.consumer.example.model.UserInfo;

public class UserInfoComparator implements Comparator<UserInfo> {

	@Override
	public int compare(UserInfo o1, UserInfo o2) {
		return (o1.getUserId() > o2.getUserId() ? 1 : -1) ;
	}

}
