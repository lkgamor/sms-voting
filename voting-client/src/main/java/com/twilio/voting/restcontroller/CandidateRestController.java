package com.twilio.voting.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.voting.interfaces.CandidateService;
import com.twilio.voting.model.Candidate;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/candidate")
public class CandidateRestController {

	private final CandidateService candidateService;
	
	@GetMapping()
	public List<Candidate> FetchAllCandidates() {
		return candidateService.FetchAllCandidates();
	}
	
	@GetMapping("/{candidateId}")
	public Optional<Candidate> FetchCandidateDetails(@PathVariable String candidateId) {
		return candidateService.FetchCandidateDetailsById(candidateId);
	}
	
	@PostMapping()
	public void RegisterCandidate(@RequestBody Candidate candidate) {
		candidateService.RegisterCandidate(candidate);
	}
	
	@PutMapping("/{candidateId}")
	public void UpdateCandidateInfo(@PathVariable String candidateId, @RequestBody Candidate candidate) throws NotFoundException {
		candidateService.UpdateCandidate(candidateId, candidate);
	}
	
	@DeleteMapping("/{candidateId}")
	public void RemoveCandidate(@PathVariable String candidateId) throws NotFoundException {
		candidateService.RemoveCandidate(candidateId);
	}
}
