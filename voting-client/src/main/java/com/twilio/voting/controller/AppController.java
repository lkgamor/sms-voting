package com.twilio.voting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.twilio.voting.model.Candidate;
import com.twilio.voting.restcontroller.CandidateRestController;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AppController {
	
	private final CandidateRestController candidateRestController;

	@GetMapping({"/", "/voting"})
	String getIndexpage() {
		return "pages/index";
	}
	
	@GetMapping("/candidate")
	String getNewCandidatePage(Candidate candidate, Model model) {
		model.addAttribute("pageTitle", "Add A New Candidate");
		return "pages/candidate";
	}
	
	@GetMapping("/candidates")
	String getUpdateCandidatePage(@RequestParam(value="id") String candidateId, Model model) {
		System.out.println(candidateId);
		Candidate candidateToUpdate = candidateRestController.FetchCandidateDetails(candidateId);
		model.addAttribute("candidate", candidateToUpdate);
		model.addAttribute("pageTitle", "Update Candidate");
		return "pages/candidate";
	}
}
