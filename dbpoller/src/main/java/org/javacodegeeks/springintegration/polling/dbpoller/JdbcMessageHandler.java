package org.javacodegeeks.springintegration.polling.dbpoller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.pojos.CustomerPayload;
import com.common.pojos.CustomerPayloadDB;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JdbcMessageHandler {

	/**
	 * Service activator method
	 * @param message
	 */
	public void recieveRecords(List<Map<String, Object>> message) {
		for (Map<String, Object> resultMap: message) {
			ObjectMapper mapper = new ObjectMapper();
			CustomerPayloadDB payload = mapper.convertValue(resultMap, CustomerPayloadDB.class);
			System.out.println("Row = " +payload.toString());
		}
	}
}
