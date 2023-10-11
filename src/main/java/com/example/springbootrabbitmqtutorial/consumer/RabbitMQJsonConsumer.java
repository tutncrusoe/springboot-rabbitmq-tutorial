package com.example.springbootrabbitmqtutorial.consumer;

import com.example.springbootrabbitmqtutorial.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQStringConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(UserDto userDto) {
        LOGGER.info(String.format("Received message -> %s", userDto.toString()));
    }
}