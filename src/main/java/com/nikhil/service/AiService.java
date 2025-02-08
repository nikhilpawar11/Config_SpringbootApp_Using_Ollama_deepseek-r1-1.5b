package com.nikhil.service;

import reactor.core.publisher.Flux;

public interface AiService {
	
	public String askAi(String question);
	
	public Flux<String> streamResponse(String question);

}
