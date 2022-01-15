package com.example.producerservice.service;

import com.example.commonmodel.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private final Logger log = LoggerFactory.getLogger(Producer.class);
    private final KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(Message message) {
        log.info("Producing the message: {}", message.toString());
        kafkaTemplate.send("messages", message);
    }
}
