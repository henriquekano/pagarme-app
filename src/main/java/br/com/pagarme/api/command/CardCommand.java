package br.com.pagarme.api.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pagarme.api.answer.CardAnswer;
import br.com.pagarme.api.exception.CardRegistrationException;
import br.com.pagarme.api.exception.PagarmeAPIException;

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
		String url = ENDPOINT + CARD_PATH;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("api_key", APIKEY);
		params.add("card_hash", cardHash);
		try {
			return request(url, HttpMethod.POST, params, CardAnswer.class);
		} catch (PagarmeAPIException e) {
			throw new CardRegistrationException(e.getError());
		}
	}
	
	public CardAnswer retrieve(String cardId) throws CardRegistrationException{
		String url = ENDPOINT + CARD_PATH + "/" + cardId + "?api_key=" + APIKEY;
		try {
			return request(url, HttpMethod.GET, null, CardAnswer.class);
		} catch (PagarmeAPIException e) {
			throw new CardRegistrationException(e.getError());
		}
	}
}
