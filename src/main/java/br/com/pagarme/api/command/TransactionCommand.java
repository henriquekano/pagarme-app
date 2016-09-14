package br.com.pagarme.api.command;

import java.io.IOException;
import java.text.MessageFormat;

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

import br.com.pagarme.api.answer.ErrorAnswer;
import br.com.pagarme.api.answer.TransactionAnswer;
import br.com.pagarme.api.exception.CancelException;
import br.com.pagarme.api.exception.PaymentException;


@Component
public class TransactionCommand extends Command{

	private final String TRANSACTION_PATH = "/transactions";
	private final String CANCEL_PATH = "/{0}/refund";
	
	@Autowired
	public TransactionCommand(RestTemplate restTemplate, 
			@Value("${pagarme.endpoint:}") String endpoint, 
			@Value("${pagarme.apikey:}") String apikey,
			ObjectMapper objectMapper) {
		super(restTemplate, endpoint, apikey, objectMapper);
	}
	
	public TransactionAnswer oneTimeTransaction(String cardHash, Integer amountInCents) throws PaymentException{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("api_key", APIKEY);
		params.add("amount", amountInCents.toString());
		params.add("card_hash", cardHash);
		try{
			TransactionAnswer ans = getRestTemplate().postForEntity(ENDPOINT + TRANSACTION_PATH, params, TransactionAnswer.class).getBody();
			return ans;
		}catch(HttpStatusCodeException e){
			String jsonError = e.getResponseBodyAsString();
			ErrorAnswer error;
			try {
				error = getObjectMapper().readValue(jsonError, ErrorAnswer.class);
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

	public void cancel(String transactionRestApiId) throws CancelException{
		String url = MessageFormat.format(ENDPOINT + TRANSACTION_PATH + CANCEL_PATH, transactionRestApiId);
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("api_key", APIKEY);
		
		try{
			getRestTemplate().postForEntity(url, params, TransactionAnswer.class);
		}catch(HttpStatusCodeException e){
			String jsonError = e.getResponseBodyAsString();
			ErrorAnswer error;
			try {
				error = getObjectMapper().readValue(jsonError, ErrorAnswer.class);
				throw new CancelException(error);
			} catch (JsonParseException e1) {
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
