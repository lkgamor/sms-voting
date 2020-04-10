package com.twilio.voting.service;

import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import com.twilio.voting.config.TwilioConfiguration;
import com.twilio.voting.model.SmsBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TwilioSmsSender {

	private final TwilioConfiguration twilioConfiguration;

	public void sendSms(SmsBody smsBody) {
		if(isValidPhoneNumber(smsBody.getPhoneNumber())) {
			PhoneNumber to = new PhoneNumber(smsBody.getPhoneNumber());
			PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
			String body = smsBody.getMessage();
			MessageCreator creator = Message.creator(to, from, body);
			creator.create();
			log.info("SENT SMS ==>> " + body + " TO ==>> " + to);
		}
		else {
			throw new IllegalArgumentException("Phone Number {" + smsBody.getPhoneNumber() + "} is not valid");
		}
	}

	private boolean isValidPhoneNumber(String phoneNumber) {
		// TODO Implement Phone Number validation
		return true;
	}

}
