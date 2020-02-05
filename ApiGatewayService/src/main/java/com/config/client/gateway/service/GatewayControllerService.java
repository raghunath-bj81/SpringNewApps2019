package com.config.client.gateway.service;

import java.net.ConnectException;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import com.common.pojos.CustomerPayload;

/**
 * Controller service proxy to hit the customer related service API's
 * @author rjosula
 *
 */
@RefreshScope
public interface GatewayControllerService {

	/**
	 * Retry mechanism to hit the getCustomer Service with configurable number of times.
	 * If any connection issues occures then the recovery method shall be called.
	 * @param isRetryEnabled
	 * @param isRetryfallback
	 * @return
	 * @throws ConnectException
	 */
	@Retryable(value = { ConnectException.class }, maxAttemptsExpression = "${customer.gateway.max.attempts}")
    public ResponseEntity<?> getAllCustomers(boolean isRetryEnabled, boolean isRetryfallback)  throws ConnectException;

	/**
	 * Saves the customer payload and retries for configured number of times.
	 * 
	 * @param customerPayload
	 * @param isRetryEnabled
	 * @param isRetryfallback
	 * @return
	 */
	@Retryable(value = { ConnectException.class }, maxAttemptsExpression = "${customer.gateway.max.attempts}")
	public ResponseEntity<?> saveCustomer(CustomerPayload customerPayload, boolean isRetryEnabled, boolean isRetryfallback);
 
	/**
	 * Fallback method when those number hits still the customer server is not up & running.
	 * @param e
	 * @return
	 */
    @Recover
    public ResponseEntity<?> customerServiceFallback(RuntimeException e);
}
