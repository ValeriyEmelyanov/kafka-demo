package com.example.producerservice.controller;

import com.example.commonmodel.model.Message;
import com.example.producerservice.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private final Producer producer;

    @Autowired
    public HomeController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping("/messages")
    public String sendMessage(@RequestParam String message, @RequestParam int number) {
        producer.produce(new Message(message, number));
        return "Ok";
    }
}
