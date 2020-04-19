package com.twilio.voting.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.twilio.voting.model.Candidate;
import com.twilio.voting.model.CandidateSave;

import javassist.NotFoundException;

@Service
public interface CandidateService {

	List<Candidate> FetchAllCandidates();
	
	Candidate FetchCandidateDetailsById(String candidateId);
	
	Candidate FetchCandidateDetailsByName(String candidateName);
	
	void RegisterCandidate(CandidateSave candidateToSave);
	
	Boolean UpdateCandidate(String candidateId, CandidateSave candidateToUpdate) throws NotFoundException;
	
	void UpdateCandidateVotes(String candidateId, Integer votes);
	
	void RemoveCandidate(String candidateId) throws NotFoundException;

	void RemoveCandidateImage(String candidateId);
}
