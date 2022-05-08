package com.kafka.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final static String TOPIC_NAME = "topic-demo";

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/send")
    public String sendMsg() {
        kafkaTemplate.send(TOPIC_NAME, 0, "key", "just is a msg");
        return "send success.";
    }

}
