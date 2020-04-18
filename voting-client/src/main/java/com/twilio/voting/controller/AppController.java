package com.twilio.voting.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.twilio.voting.model.Candidate;
import com.twilio.voting.restcontroller.CandidateRestController;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AppController {
	
	@Value("${spring.application.name}")
	String APPLICATION_NAME;
	
	private final CandidateRestController candidateRestController;
	
	@GetMapping("/candidate")
	String getIndexpage(Model model) {
		model.addAttribute("appName", APPLICATION_NAME);
		return "pages/index";
	}
	
	@GetMapping("/candidate/new")
	String getNewCandidatePage(Candidate candidate, Model model) throws IOException {
		model.addAttribute("pageTitle", "New Candidate");
		model.addAttribute("appName", APPLICATION_NAME);
		return "pages/candidate";
	}
	
	@GetMapping("/candidate/{candidateId}")
	String getUpdateCandidatePage(@PathVariable String candidateId, Model model) {
		Candidate candidateToUpdate = candidateRestController.FetchCandidateDetails(candidateId);
		model.addAttribute("candidate", candidateToUpdate);
		model.addAttribute("pageTitle", "Candidate Details");
		model.addAttribute("appName", APPLICATION_NAME);
		return "pages/candidate";
	}
}
