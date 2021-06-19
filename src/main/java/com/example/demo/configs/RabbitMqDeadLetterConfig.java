package com.example.demo.configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqDeadLetterConfig {

    @Value("${dl.routing.key}")
    private String routingKey;
    @Value("${dl.dead.routing.key}")
    private String deadRoutingKey;
    @Value("${dl.dead.exchange.name}")
    private String deadExchangeName;
    @Value("${dl.exchange.name}")
    private String exchangeName;

    @Value("${dl.queue.name}")
    private String queueName;

    @Value("${dl.dead.queue.name}")
    private String deadQueueName;


    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange(deadExchangeName);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    Queue dlq() {
        return QueueBuilder.durable(deadQueueName).build();
    }

    @Bean
    Queue queue() {
        return QueueBuilder.durable(queueName).withArgument("x-dead-letter-exchange", deadExchangeName)
                .withArgument("x-dead-letter-routing-key", deadRoutingKey).build();
    }

    @Bean
    Binding DLQbinding() {
        return BindingBuilder.bind(dlq()).to(deadLetterExchange()).with(deadRoutingKey);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }


}
