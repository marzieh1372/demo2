package com.example.demo.service;

import com.example.demo.dao.MqMessageRepository;
import com.example.demo.model.MqMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqMessageService {

    private static final Logger LOGGER = LogManager.getLogger(MqMessageService.class);
    @Autowired
    MqMessageRepository mqMessageRepository;


    public void saveMessage(MqMessage mqMessage) {
        LOGGER.info("Start saving message ...");
        mqMessageRepository.save(mqMessage);
    }
}
