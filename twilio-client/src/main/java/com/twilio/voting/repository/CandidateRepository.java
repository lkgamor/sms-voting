package com.twilio.voting.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.twilio.voting.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, String> {

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE Candidate c set c.totalVoteCount =:totalVote WHERE c.candidateId = :id")
	void updateCandidate(String id, Integer totalVote);

	Optional<Candidate> findByCandidateId(String string);

	List<Candidate> findAll();

	@Transactional
	@Modifying(clearAutomatically = true)
	void deleteByCandidateId(String id);
}
