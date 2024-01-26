package com.ninjaone.dundie_awards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DundieAwardsApplication {

	private static final Logger logger = LoggerFactory.getLogger(DundieAwardsApplication.class);

	public static void main(String[] args) {
		logger.info("DundieAwardsApplication started");
		SpringApplication.run(DundieAwardsApplication.class, args);
	}

}
