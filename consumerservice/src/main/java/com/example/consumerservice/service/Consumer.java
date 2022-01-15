package com.example.consumerservice.service;

import com.example.commonmodel.model.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics = "messages", groupId = "message_group_1")
    public void concume(Message message) {
        System.out.println("Consuming the message: " + message);
    }
}
