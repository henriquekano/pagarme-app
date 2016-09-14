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

import br.com.pagarme.api.exception.PaymentException;
import br.com.pagarme.application.domain.entity.Transaction;
import br.com.pagarme.api.answer.Error;


@Component
public class TransactionCommand {

	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;
	private final String ENDPOINT;
	private final String PATH = "/transactions";
	private final String APIKEY;
	
	@Autowired
	public TransactionCommand(RestTemplate restTemplate, 
			@Value("${pagarme.endpoint:}") String endpoint, 
			@Value("${pagarme.apikey:}") String apikey,
			ObjectMapper objectMapper) {
		this.restTemplate = restTemplate;
		this.ENDPOINT = endpoint;
		this.APIKEY = apikey;
		this.objectMapper = objectMapper;
	}
	
	public Transaction oneTimeTransaction(String cardHash, Integer amountInCents) throws PaymentException{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("api_key", APIKEY);
		params.add("amount", amountInCents.toString());
		params.add("card_hash", cardHash);
		try{
			return restTemplate.postForEntity(ENDPOINT + PATH, params, Transaction.class).getBody();
		}catch(HttpStatusCodeException e){
			String jsonError = e.getResponseBodyAsString();
			Error error;
			try {
				error = objectMapper.readValue(jsonError, Error.class);
				throw new PaymentException(error);
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
