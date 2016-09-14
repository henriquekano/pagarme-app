package br.com.pagarme.api.command;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pagarme.api.answer.CardAnswer;
import br.com.pagarme.api.answer.ErrorAnswer;
import br.com.pagarme.api.exception.CardRegistrationException;

@Component
public class CardCommand extends Command{

	private final String CARD_PATH = "/cards";
	
	@Autowired
	public CardCommand(RestTemplate restTemplate, 
			@Value("${pagarme.endpoint:}") String endpoint, 
			@Value("${pagarme.apikey:}") String apikey,
			ObjectMapper objectMapper) {
		super(restTemplate, endpoint, apikey, objectMapper);
	}
	
	public CardAnswer register(String cardHash) throws CardRegistrationException{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("api_key", APIKEY);
		params.add("card_hash", cardHash);
		try{
			CardAnswer card = getRestTemplate().postForEntity(ENDPOINT + CARD_PATH, params, CardAnswer.class).getBody();
			return card;
		}catch(HttpStatusCodeException e){
			String jsonError = e.getResponseBodyAsString();
			ErrorAnswer error;
			try {
				error = getObjectMapper().readValue(jsonError, ErrorAnswer.class);
				throw new CardRegistrationException(error);
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
