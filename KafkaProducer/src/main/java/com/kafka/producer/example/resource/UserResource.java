package com.kafka.producer.example.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.pojos.User;

@RestController
@RequestMapping("kafka")
@PropertySource("appConfig.properties")
public class UserResource {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    @Value("${kafka.producer.topic.name}")
    private String TOPIC;

    @GetMapping("/publish/{name}")
    public String post(@PathVariable("name") final String name) {

        kafkaTemplate.send(TOPIC, new User(name, "Technology"));

        return "Published successfully";
    }
}
