package com.kafka.consumer.example.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kafka.consumer.example.model.UserInfo;

/**
 * JPA Repository class to persist or to get the data from Database
 * @author rjosula
 *
 */
@Repository
public interface CustomerDAO extends CrudRepository<UserInfo, Integer> {

}
