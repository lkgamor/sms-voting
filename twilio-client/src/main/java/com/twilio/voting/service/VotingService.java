package com.twilio.voting.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.twilio.voting.model.Candidate;
import com.twilio.voting.model.SmsBody;
import com.twilio.voting.repository.CandidateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VotingService {

	private final CandidateService candidateService;
	private final CandidateRepository candidateRepository;
	
	public void voteForCandidate(SmsBody smsBody) {
		
		Optional<Candidate> candidate = candidateRepository.findByCandidateId(smsBody.getPhoneNumber());
		if (candidate.isPresent()) {
			initVote(candidate.get().getCandidateId(), candidate.get().getTotalVoteCount());
		} else {
			candidateService.registerCandidate(smsBody.getPhoneNumber(), smsBody.getMessage());
			initVote(smsBody.getPhoneNumber(), 0);
		}
	}

	private void initVote(String candidateId, Integer existingVotes) {
		Integer totalVotes = existingVotes + 1;
		candidateRepository.updateCandidate(candidateId, totalVotes);
	}
}
