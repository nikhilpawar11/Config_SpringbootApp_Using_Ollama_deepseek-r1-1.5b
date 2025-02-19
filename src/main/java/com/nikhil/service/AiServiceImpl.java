package com.nikhil.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.ai.chat.client.ChatClient;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikhil.payload.CricketResponse;

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


	@Override
	public CricketResponse generateCricketResponse(String inputText) {
		
		// Generate a prompt string where we instruct the AI to handle the question
        String promptString = "As a cricket expert, answer this question: " + inputText + ". " 
                + "If the above question is not related to cricket, respond with a funny joke saying this question is out of context.";
		
		String stringResponse = chatClient.prompt(promptString).call().content();
		
		// Create a Map to hold the response content
        Map<String, String> map = new HashMap<>();
        map.put("content", stringResponse);
		
        // create object of ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		
		// Serialize the response map to JSON using ObjectMapper
        String jsonResponse = "";
        try {
            jsonResponse = objectMapper.writeValueAsString(map); // Convert Map to JSON string
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception if something goes wrong during serialization
        }
		
        // Create a CricketResponse object and set the serialized JSON string
        CricketResponse cricketResponse = new CricketResponse();
        cricketResponse.setContent(jsonResponse);
		
		return cricketResponse;
	}

}
