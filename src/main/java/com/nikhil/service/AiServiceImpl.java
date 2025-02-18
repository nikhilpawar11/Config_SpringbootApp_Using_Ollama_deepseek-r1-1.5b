package com.nikhil.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AiServiceImpl implements AiService {
	
	
	private ChatClient chatClient;

	public AiServiceImpl(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}

	
	@Override
	public String askAi(String question) {
	
		return chatClient.prompt(question).call().content();
	}




	@Override
	public Flux<String> streamResponse(String question) {
		
		return chatClient.prompt().user(question).stream().content();
	}

}
