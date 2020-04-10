package com.twilio.voting.service;

import org.springframework.stereotype.Service;

import com.twilio.voting.model.Candidate;
import com.twilio.voting.repository.CandidateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateService {

	private final CandidateRepository candidateRepository;
	
	public void registerCandidate(String phoneNumber, String name) {
		
		Candidate candidate = new Candidate();
		candidate.setCandidateId(phoneNumber);
		candidate.setCandidateName(name);
		candidate.setTotalVoteCount(0);		
		candidateRepository.save(candidate);
	}
}
