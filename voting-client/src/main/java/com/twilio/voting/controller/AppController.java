package com.twilio.voting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	@GetMapping({"/", "/voting"})
	String getIndexpage() {
		return "/pages/index";
	}
	
	@GetMapping("new-candidate")
	String getTermsAndConditionPage() {
		return "/pages/new-candidate";
	}
}
