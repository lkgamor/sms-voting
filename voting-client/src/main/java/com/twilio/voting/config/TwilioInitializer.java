package com.twilio.voting.config;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j 
public class TwilioInitializer {

	private final TwilioConfiguration twilioConfiguration;

	@Autowired 
	public TwilioInitializer(TwilioConfiguration twilioConfiguration) { 
		this.twilioConfiguration = twilioConfiguration;
		Twilio.init(twilioConfiguration.getAccountSid(),
		twilioConfiguration.getAuthToken());
		log.info("Twilio Initialized for account_sid ==>> " + twilioConfiguration.getAccountSid()); 
	}

}
