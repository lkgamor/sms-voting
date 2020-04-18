package com.twilio.voting.model;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.Data;

@Data
public class CandidateSave implements Serializable {

	private static final long serialVersionUID = -9197895324257608580L;

	private String candidateId;
	
	private String candidateName;
	
	private String candidateEmail;
	
	private CandidateImage candidateImage;

	private Integer totalVoteCount;
	
}
