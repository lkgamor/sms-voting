package com.twilio.voting.service;

import org.springframework.stereotype.Service;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TwilioSmsReceiver {

	public String receiveSms() {
		Body body = new Body.Builder("I love you too").build();
		Message sms = new Message.Builder().body(body).build();
		MessagingResponse response = new MessagingResponse.Builder().message(sms).build();
		log.info("RESPONDED SMS ==>> " + response.toXml());
		return response.toXml();		
	}
}
