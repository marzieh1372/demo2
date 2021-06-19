package com.example.demo.controller;

import com.example.demo.model.MqMessage;
import com.example.demo.service.MqMessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class RabbitMQTopicWebController {

    private static final Logger LOGGER = LogManager.getLogger(RabbitMQTopicWebController.class);


    @Autowired
    MqMessageService mqMessageService;

    @Autowired
    private AmqpTemplate amqpTemplate;



    @PostMapping(value = "/putmessage")
    public String producer(@RequestBody MqMessage mqMessage) {
        LOGGER.info("Starting producer method {}");
        amqpTemplate.convertAndSend(mqMessage.getExchangeName(), mqMessage.getRoutingKey(), mqMessage.getMessageData());
        LOGGER.debug("Sent Message to " + mqMessage.getExchangeName() + " with this routing key " + mqMessage.getRoutingKey());
        return "Message sent to the RabbitMQ ...";
    }
}
