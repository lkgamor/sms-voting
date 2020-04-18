package com.twilio.voting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.twilio.voting.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, String> {
	
	Optional<Candidate> findByCandidateId(String id);
	
	Optional<Candidate> findByCandidateName(String candidateName);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE Candidate c set c.candidateName =:candidateName, c.candidateEmail =:candidateEmail, c.candidateImage =:candidateImage WHERE c.candidateId = :id")
	void updateCandidateInfo(String id, String candidateName, String candidateEmail, String candidateImage);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE Candidate c set c.candidateName =:candidateName, c.candidateEmail =:candidateEmail WHERE c.candidateId = :id")
	void updateCandidateNameAndEmail(String id, String candidateName, String candidateEmail);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE Candidate c set c.totalVoteCount =:totalVote WHERE c.candidateId = :id")
	void updateCandidateVotes(String id, Integer totalVote);

	@Transactional
	@Modifying(clearAutomatically = true)
	void deleteByCandidateId(String id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE Candidate c set c.candidateImage ='' WHERE c.candidateId = :id")
	void deleteCandidateImage(String id);
}
