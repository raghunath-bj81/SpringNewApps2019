package com.config.client.gateway.service;

import java.net.ConnectException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.common.pojos.CustomerPayload;
import com.common.pojos.ErrorResponse;
import com.config.client.gateway.controller.CustomerGatewayApp;
/**
 * Controller service Implementation proxy to hit the customer related service API's
 * @author rjosula
 *
 */
@Service
@RefreshScope
public class GatewayControllerServiceImpl implements GatewayControllerService {

	@Autowired
	public CustomerGatewayApp customerGatewayApp;

	@Value("${customer.gateway.max.attempts}")
	private String maxAttempts;

	/**
	 * Retry mechanism to hit the getCustomer Service with configurable number of times.
	 * If any connection issues occures then the recovery method shall be called.
	 * @param isRetryAlive
	 * @param isRetryfallback
	 * @return
	 * @throws ConnectException
	 */
	@Override
	public ResponseEntity<?> getAllCustomers(boolean isRetryAlive, boolean isRetryfallback) throws ConnectException {
		ResponseEntity<?> response = null;
		if (isRetryAlive) {
			System.out.println("Customer service is down and recall of Customer service is configured true, so trying to connect the same.");

			try {
				if (isRetryfallback) {
					throw new ConnectException(
							"Retry shall occur for configured number of times..Must fallback as all retry will get exception!!!");
				}
			} catch (Exception e) {
			}
			System.out.println("Customer service is now up and running... Getting the results..");
			response = customerGatewayApp.getAllCustomers();
			try {
				if (response == null) {
					throw new ConnectException("Don't worry!! Just Simulated for Spring-retry..");
				}
			} catch (Exception e) {
			}
		}

		return response;
	}

	/**
	 * Fallback method when those number hits still the customer server is not up & running.
	 * @param e
	 * @return
	 */
	@Override
	public ResponseEntity<?> customerServiceFallback(RuntimeException e) {
		ErrorResponse errResponse = new ErrorResponse();
		errResponse.setErrorCode("CustomerService Down");
		errResponse.setErrorDescription("Customer Server application is not up & running though "+maxAttempts + " retries have been tried");
		return new ResponseEntity<ErrorResponse>(errResponse, HttpStatus.OK);
	}

	/**
	 * Saves the customer payload and retries for configured number of times.
	 * 
	 * @param customerPayload
	 * @param isRetryAlive
	 * @param isRetryfallback
	 * @return
	 */
	@Override
	public ResponseEntity<?> saveCustomer(CustomerPayload customerPayload, boolean isRetryAlive,
			boolean isRetryfallback) {
		ResponseEntity<?> response = null;
		if (isRetryAlive) {
			System.out.println("Customer service is down and recall of Customer service is configured true, so trying to connect the same.");

			try {
				if (isRetryfallback) {
					throw new ConnectException(
							"Retry shall occur for configured number of times..Must fallback as all retry will get exception!!!");
				}
			} catch (Exception e) {
			}
			System.out.println("Customer service is now up and running... Saving the results..");
			response = customerGatewayApp.saveCustomer(customerPayload);
			try {
				if (response == null) {
					throw new ConnectException("Don't worry!! Just Simulated for Spring-retry..");
				}
			} catch (Exception e) {
			}
		}
		return response;
	}

}
