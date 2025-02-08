package com.nikhil.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

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

}
