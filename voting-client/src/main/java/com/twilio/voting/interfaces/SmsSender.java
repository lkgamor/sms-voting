package com.twilio.voting.interfaces;

import com.twilio.twiml.MessagingResponse;
import com.twilio.voting.model.SmsBody;

public interface SmsSender {

	void sendSms(SmsBody smsRequest);
	
	MessagingResponse receiveSms();
}
