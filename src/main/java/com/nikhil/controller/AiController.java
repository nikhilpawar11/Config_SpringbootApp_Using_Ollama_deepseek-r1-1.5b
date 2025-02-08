package com.nikhil.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.service.AiService;

@RestController
@RequestMapping("/ai")
public class AiController {
	
	private AiService aiService;
	
	
	public AiController(AiService aiService) {
		this.aiService = aiService;
	}



	@GetMapping("/askToAi")
	public ResponseEntity<String> askToAi(
			@RequestParam(value = "query", required = false, defaultValue = "How are you ? How can you help me ?") String query
			){
		
		String response = aiService.askAi(query);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}

}
