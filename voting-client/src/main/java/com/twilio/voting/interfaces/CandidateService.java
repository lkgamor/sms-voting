package com.twilio.voting.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.twilio.voting.model.Candidate;

import javassist.NotFoundException;

@Service
public interface CandidateService {

	List<Candidate> FetchAllCandidates();
	
	Candidate FetchCandidateDetailsById(String candidateId);
	
	Candidate FetchCandidateDetailsByName(String candidateName);
	
	void RegisterCandidate(Candidate candidate);
	
	void UpdateCandidate(String candidateId, Candidate candidate) throws NotFoundException;
	
	void UpdateCandidateVotes(String candidateId, Integer votes);
	
	void RemoveCandidate(String candidateId) throws NotFoundException;
}
