package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
	private static final Logger LOGGER = LogManager.getLogger(Receiver.class);

	public void receiveMessage(String message) {
		LOGGER.info("Received < "+message+" >");

	}


}
