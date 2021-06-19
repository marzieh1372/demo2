package com.example.demo;

import com.example.demo.model.MqMessage;
import com.example.demo.service.MqMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);
    @Autowired
    MqMessageService mqMessageService;


    @RabbitListener(queues = "testS.queue")
    public void recievedMessage(String mqMessage) {
        logger.info("Recieved Message From RabbitMQ: " + mqMessage);
        MqMessage savedMsg = new MqMessage();
        savedMsg.setMessageData(mqMessage);
        savedMsg.setExchangeName("deadLetter");
        savedMsg.setRoutingKey("deadLetter");
        mqMessageService.saveMessage(savedMsg);
    }
}
