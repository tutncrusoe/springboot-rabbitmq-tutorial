package com.example.springbootrabbitmqtutorial.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQStringConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQStringConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.string.name}"})
     public void consume(String message) {
        LOGGER.info(String.format("Received message -> %s", message));
     }
}
