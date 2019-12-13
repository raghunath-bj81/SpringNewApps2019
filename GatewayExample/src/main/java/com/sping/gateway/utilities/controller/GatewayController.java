package com.sping.gateway.utilities.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sping.gateway.utilities.service.GatewayService;

@RestController
@RequestMapping("/gateway/example")
public class GatewayController {

	@Autowired
	private GatewayService gatewayService;
	
	@RequestMapping("/default/{username}")
	public String testMessage(@PathVariable("username") String username)
	{
		Map<String, Object> headers = new HashMap<>();
		headers.put("content-type", "application/json");
		MessageHeaders header = new MessageHeaders(headers);
		Message<String> msg = MessageBuilder.createMessage(username, header);
		
		Message<String> returnMsg = gatewayService.send(msg);
		
		return returnMsg.getPayload();
	}
}
