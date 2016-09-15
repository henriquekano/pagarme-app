package br.com.pagarme.api.command;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pagarme.api.answer.Answer;
import br.com.pagarme.api.answer.ErrorAnswer;
import br.com.pagarme.api.exception.PagarmeAPIException;

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
	
	protected <T extends Answer> T request(String url, HttpMethod method, MultiValueMap<String, String> params, Class<T> clazz) throws PagarmeAPIException{
		try{
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, null);
			return getRestTemplate().exchange(url, method, entity, clazz).getBody();
		}catch(HttpStatusCodeException e){
			String jsonError = e.getResponseBodyAsString();
			ErrorAnswer error;
			try {
				error = getObjectMapper().readValue(jsonError, ErrorAnswer.class);
				throw new PagarmeAPIException(error);
			} catch (JsonParseException e1) {
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		}
	}
	
}
