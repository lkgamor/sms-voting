package com.twilio.voting.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.twilio.voting.model.Candidate;

import javassist.NotFoundException;

@Service
public interface CandidateService {

	List<Candidate> FetchAllCandidates();
	
	Optional<Candidate> FetchCandidateDetailsById(String candidateId);
	
	Optional<Candidate> FetchCandidateDetailsByName(String candidateName);
	
	void RegisterCandidate(Candidate candidate);
	
	void UpdateCandidate(String candidateId, Candidate candidate) throws NotFoundException;
	
	void UpdateCandidateVotes(String candidateId, Integer votes);
	
	void RemoveCandidate(String candidateId) throws NotFoundException;
}
