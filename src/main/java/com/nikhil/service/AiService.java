package com.nikhil.service;

import com.nikhil.payload.CricketResponse;

import reactor.core.publisher.Flux;

public interface AiService {
	
	public String askAi(String question);
	
	public Flux<String> streamResponse(String question);
	
	public CricketResponse generateCricketResponse(String inputText);
	
}
