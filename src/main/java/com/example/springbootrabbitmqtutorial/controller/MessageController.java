package com.example.springbootrabbitmqtutorial.controller;

import com.example.springbootrabbitmqtutorial.dto.UserDto;
import com.example.springbootrabbitmqtutorial.publisher.RabbitMQJsonProducer;
import com.example.springbootrabbitmqtutorial.publisher.RabbitMQStringProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    @Autowired
    private RabbitMQStringProducer rabbitMQStringProducer;

    @Autowired
    private RabbitMQJsonProducer rabbitMQJsonProducer;

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        this.rabbitMQStringProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ.");
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody UserDto userDto) {
        rabbitMQJsonProducer.sendJsonMessage(userDto);
        return ResponseEntity.ok("Json message sent to RabbitMQ.");
    }
}
