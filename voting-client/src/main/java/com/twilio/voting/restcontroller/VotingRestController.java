package com.twilio.voting.restcontroller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.voting.model.SmsBody;
import com.twilio.voting.service.TwilioSmsService;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/sms")
public class VotingRestController {

	private final TwilioSmsService twilioSmsService;
	
	@PostMapping("/send")
	public void sendSms(@Valid @RequestBody SmsBody smsBody) throws NotFoundException {
		twilioSmsService.sendSms(smsBody);
	}
	
	@PostMapping("/reply")
	public String receiveSms() {		
		return twilioSmsService.receiveSms();
	}
}
