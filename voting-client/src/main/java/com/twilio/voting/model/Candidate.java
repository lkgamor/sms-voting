package com.twilio.voting.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Candidate implements Serializable {

	private static final long serialVersionUID = -159405765942573329L;

	@Id
	@Column(name="candidate_id")
	private String candidateId;
	
	@Column(name="candidate_name")
	private String candidateName;
	
	@Column(name="candidate_email")
	private String candidateEmail;
	
	@Column(name="candidate_image")
	private byte[] candidateImage;

	@Column(name="total_vote_count")
	private Integer totalVoteCount;	

}
