package br.com.pagarme.api.command;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Command {

	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;
	public final String ENDPOINT;
	public final String APIKEY;
	
	public Command(RestTemplate restTemplate, 
			String endpoint, 
			String apikey,
			ObjectMapper objectMapper) {
		this.restTemplate = restTemplate;
		this.ENDPOINT = endpoint;
		this.APIKEY = apikey;
		this.objectMapper = objectMapper;
	}
	
	protected RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	protected ObjectMapper getObjectMapper() {
		return objectMapper;
	}
	
}
