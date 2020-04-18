package com.twilio.voting.restcontroller;

import java.util.List;

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
import com.twilio.voting.model.CandidateSave;

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
	public Candidate FetchCandidateDetails(@PathVariable String candidateId) {
		return candidateService.FetchCandidateDetailsById(candidateId);
	}
	
	@PostMapping()
	public void RegisterCandidate(@RequestBody CandidateSave candidateToSave) {
		candidateService.RegisterCandidate(candidateToSave);
	}
	
	@PutMapping("/{candidateId}")
	public void UpdateCandidateInfo(@PathVariable String candidateId, @RequestBody CandidateSave candidateToUpdate) throws NotFoundException {
		candidateService.UpdateCandidate(candidateId, candidateToUpdate);
	}
	
	@DeleteMapping("/{candidateId}/image")
	public void RemoveCandidateImage(@PathVariable String candidateId) throws NotFoundException {
		candidateService.RemoveCandidateImage(candidateId);
	}
	
	@DeleteMapping("/{candidateId}")
	public void RemoveCandidate(@PathVariable String candidateId) throws NotFoundException {
		candidateService.RemoveCandidate(candidateId);
	}
}
