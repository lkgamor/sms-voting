package com.twilio.voting.service;

import org.springframework.stereotype.Service;

import com.twilio.voting.model.SmsBody;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TwilioSmsService {

	private final TwilioSmsSender twilioSmsSender;

	private final TwilioSmsReceiver twilioSmsReceiver;
	
	private final VotingService votingService;
	
	public void sendSms(SmsBody smsBody) throws NotFoundException {
		twilioSmsSender.sendSms(smsBody);
		votingService.voteForCandidate(smsBody);
	}
	
	public String receiveSms() {
		return twilioSmsReceiver.receiveSms();
	}
}
