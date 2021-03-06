package com.twilio.voting.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.twilio.voting.model.Candidate;
import com.twilio.voting.model.SmsBody;
import com.twilio.voting.repository.CandidateRepository;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VotingService {

	private final CandidateRepository candidateRepository;
	
	public void voteForCandidate(SmsBody smsBody) throws NotFoundException {
		
		Optional<Candidate> candidate = candidateRepository.findByCandidateName(smsBody.getMessage());
		if (candidate.isPresent()) {
			Integer totalVotes = candidate.get().getTotalVoteCount() + 1;
			candidateRepository.updateCandidateVotes(candidate.get().getCandidateId(), totalVotes);
		} else {
			throw new NotFoundException("No Candidate with ID [" + candidate.get().getCandidateId() + "] to VOTE for!");
		}
	}
}
