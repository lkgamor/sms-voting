package com.twilio.voting.model;

import lombok.Data;

@Data
public class CandidateImage {

	private String name;
	
	private Integer size;
	
	private String type;
	
	private byte[] data;
}
