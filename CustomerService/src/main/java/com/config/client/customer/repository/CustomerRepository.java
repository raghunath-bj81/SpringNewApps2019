/**
 * 
 */
package com.config.client.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.config.client.customer.model.Customer;

/**
 * @author rjosula
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
