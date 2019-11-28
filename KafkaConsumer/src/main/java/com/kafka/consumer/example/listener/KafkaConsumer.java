package com.kafka.consumer.example.listener;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.common.pojos.User;
import com.kafka.consumer.example.model.UserInfo;
import com.kafka.consumer.example.service.CustomerService;
import com.kafka.consumer.example.util.UserInfoComparator;

/**
 * This is a Consumer service to consume all the topics data
 * @author rjosula
 *
 */
@Service
public class KafkaConsumer {
	
	/** Logger instance to get the KafkaConsumer class logs*/
	Logger logger = Logger.getLogger(KafkaConsumer.class);
	
	@Autowired
	private CustomerService customerService;

    @KafkaListener(topics = "Kafka_Example", group = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }

    /**
     * Kafka Listener to consume the User information data
     * @param user
     */
    @KafkaListener(topics = "Kafka_Example_json", group = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public void consumeUserInformation(User user) {
    	logger.info("Inside Consumer to get the User Topic");
    	List<UserInfo> listOfUsers = customerService.findAllUsers();
    	Integer sequenceNumber = 0;
    	Optional<UserInfo> optionalUser = Optional.empty();
    	if(null != listOfUsers) {
    		optionalUser = listOfUsers.stream().max(new UserInfoComparator());
    		if(optionalUser.isPresent()) {
    			sequenceNumber = optionalUser.get().getUserId();
    		}
    	}
    	logger.info("Next Sequence Number for inserting the user");
    	UserInfo userInfo = new UserInfo(sequenceNumber+1,user.getName(), user.getDept());
    	try {
    		UserInfo userSaved = customerService.saveUserInfo(userInfo);
    		if(userSaved != null) {
    			logger.info("UserInfo has been saved into UserDB successfully");
    		} else {
    			logger.info("There is some issue while saving the UserInfo");
    		}
    	} catch(Exception ex) {
    		ex.printStackTrace();
    		logger.error("Error occurred while consuming or saving the User information", ex);
    	}
    }
}
