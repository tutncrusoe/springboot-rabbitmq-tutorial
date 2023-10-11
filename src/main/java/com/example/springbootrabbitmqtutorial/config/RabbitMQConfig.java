package com.example.springbootrabbitmqtutorial.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.string.name}")
    private String stringQueue;

    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key.string}")
    private String routingKeyString;

    @Value("${rabbitmq.routing.key.json}")
    private String routingKeyJson;

    // spring bean for rabbitmq queue string
    @Bean
    public Queue stringQueue() {
        return new Queue(stringQueue);
    }

    // spring bean for rabbitmq queue json
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }

    // spring bean for rabbitmq  exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    // binding between queue and exchange using routing_key
    @Bean
    public Binding stringBinding() {
        return BindingBuilder
                .bind(stringQueue())
                .to(exchange())
                .with(routingKeyString);
    }

    // binding between queue and exchange using routing_key
    @Bean
    public Binding jsonBinding() {
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(routingKeyJson);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    /*
    Spring boot automatically created all beans of them.
     */
    // Connection Factory
    // RabbitTemplate
    // RabbitAdmin
}
