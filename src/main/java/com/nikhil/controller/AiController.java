package com.nikhil.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nikhil.service.AiService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AiController {

	private AiService aiService;

	public AiController(AiService aiService) {
		this.aiService = aiService;
	}

	@GetMapping("/askToAi")
	public ResponseEntity<String> askToAi(
			@RequestParam(value = "question", required = false, defaultValue = "How are you ?") String question
			){

		String response = aiService.askAi(question);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	// so we want to get data in the form of chunks so for that we use reactive programming using FLUX
	@GetMapping("/streamAi")
	public ResponseEntity<Flux<String>> streamAi(
			@RequestParam(value = "question", required = false, defaultValue = "How are you ?") String question
			){

		Flux<String> streamResponse = aiService.streamResponse(question);

		return new ResponseEntity<>(streamResponse, HttpStatus.OK);
	}
	
}
