package com.sping.gateway.utilities.service;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public interface GatewayService {

	public Message<String> send(Message<String> message);
}
