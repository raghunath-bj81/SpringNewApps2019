package com.sping.gateway.utilities.service;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ServiceAtc {

	public String changeMsg(Message<String> message) {
		System.out.println("Inside the service activator..");
		return message.getPayload()  + " Inside the service activator..";
	}
}
