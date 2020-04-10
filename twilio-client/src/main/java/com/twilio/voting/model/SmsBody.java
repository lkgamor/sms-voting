package com.twilio.voting.model;

import javax.validation.constraints.NotBlank;

public class SmsBody {

	@NotBlank
	private String phoneNumber;
	@NotBlank
	private String message;
	
	public SmsBody(String phoneNumber, String message) {
		this.phoneNumber = phoneNumber;
		this.message = message;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
