package br.com.pagarme.api.command;

import java.io.IOException;
import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
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
import br.com.pagarme.api.answer.PayableAnswer;
import br.com.pagarme.api.answer.SplitRule;
import br.com.pagarme.api.answer.TransactionAnswer;
import br.com.pagarme.api.enums.PaymentMethod;
import br.com.pagarme.api.exception.CancelException;
import br.com.pagarme.api.exception.PagarmeAPIException;
import br.com.pagarme.api.exception.PaymentException;


@Component
public class TransactionCommand extends Command{

	private final String TRANSACTION_PATH = "/transactions";
	private final String CANCEL_PATH = "/{0}/refund";
	private final String PAYABLES_PATH = "/{0}/payables";
	
	@Autowired
	public TransactionCommand(RestTemplate restTemplate, 
			@Value("${pagarme.endpoint:}") String endpoint, 
			@Value("${pagarme.apikey:}") String apikey,
			ObjectMapper objectMapper) {
		super(restTemplate, endpoint, apikey, objectMapper);
	}
	
	public TransactionAnswer oneTimeTransaction(String cardHash, Integer amountInCents, PaymentMethod paymentMethod) throws PaymentException{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("api_key", APIKEY);
		params.add("amount", amountInCents.toString());
		params.add("card_hash", cardHash);
		params.add("payment_method", paymentMethod.toString());
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
	
	public TransactionAnswer oneTimeTransaction(String cardHash, Integer amountInCents, 
			SplitRule[] rules, PaymentMethod paymentMethod) throws PagarmeAPIException{
		String url = ENDPOINT + TRANSACTION_PATH;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("api_key", APIKEY);
		params.add("amount", amountInCents.toString());
		params.add("card_hash", cardHash);
		params.add("payment_method", paymentMethod.toString());
		for(int i = 0; i < rules.length; i++){
			params.add("split_rules[" + i + "][recipient_id]", rules[i].getRecipient_id());
			params.add("split_rules[" + i + "][charge_processing_fee]", rules[i].getCharge_processing_fee().toString());
			params.add("split_rules[" + i + "][liable]", rules[i].getLiable().toString());
			params.add("split_rules[" + i + "][percentage]", rules[i].getPercentage());
		}
		
		return request(url, HttpMethod.POST, params, TransactionAnswer.class);
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

	public CardAnswer retrieveCardByTransaction(String transactionAPIId) throws PagarmeAPIException{
		String url = ENDPOINT + TRANSACTION_PATH + "/" + transactionAPIId;
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("api_key", APIKEY);
		
		try{
			return getRestTemplate().postForEntity(url, params, CardAnswer.class).getBody();
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

	public TransactionAnswer pay(String cardId, Integer amountInCents, String name, 
			String document_number, String email, String street, String neighborhood, 
			String zipcode, String street_number, String complementary, String ddd, 
			String number, PaymentMethod paymentMethod) throws PaymentException{
		String url = ENDPOINT + TRANSACTION_PATH;
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("api_key", APIKEY);
		params.add("amount", amountInCents.toString());
		if(cardId != null){
			params.add("card_id", cardId);
		}
		params.add("payment_method", paymentMethod.toString());
		params.add("customer[name]", name);
		params.add("customer[document_number]", document_number);
		params.add("customer[email]", email);
		params.add("customer[address][street]", street);
		params.add("customer[address][neighborhood]", neighborhood);
		params.add("customer[address][zipcode]", zipcode);
		params.add("customer[address][street_number]", street_number);
		params.add("customer[address][complementary]", complementary);
		params.add("customer[phone][ddd]", ddd);
		params.add("customer[phone][number]", number);
		try{
			return getRestTemplate().postForEntity(url, params, TransactionAnswer.class).getBody();
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

	public TransactionAnswer pay(String cardId, Integer amountInCents, String name, 
			String document_number, String email, String street, String neighborhood, 
			String zipcode, String street_number, String complementary, String ddd, 
			String number, SplitRule[] rules, PaymentMethod paymentMethod) throws PagarmeAPIException{
		String url = ENDPOINT + TRANSACTION_PATH;
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("api_key", APIKEY);
		params.add("amount", amountInCents.toString());
		if(cardId != null){
			params.add("card_id", cardId);
		}
		
		params.add("payment_method", paymentMethod.toString());
		params.add("customer[name]", name);
		params.add("customer[document_number]", document_number);
		params.add("customer[email]", email);
		params.add("customer[address][street]", street);
		params.add("customer[address][neighborhood]", neighborhood);
		params.add("customer[address][zipcode]", zipcode);
		params.add("customer[address][street_number]", street_number);
		params.add("customer[address][complementary]", complementary);
		params.add("customer[phone][ddd]", ddd);
		params.add("customer[phone][number]", number);
		for(int i = 0; i < rules.length; i++){
			params.add("split_rules[" + i + "][recipient_id]", rules[i].getRecipient_id());
			params.add("split_rules[" + i + "][charge_processing_fee]", rules[i].getCharge_processing_fee().toString());
			params.add("split_rules[" + i + "][liable]", rules[i].getLiable().toString());
			params.add("split_rules[" + i + "][percentage]", rules[i].getPercentage());
		}
		
		return request(url, HttpMethod.POST, params, TransactionAnswer.class);
	}
	
	
	
	public TransactionAnswer testBoletoPay(String transactionAPIId) throws PagarmeAPIException{
		String url = ENDPOINT + TRANSACTION_PATH + "/" + transactionAPIId;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("api_key", APIKEY);
		params.add("status", "paid");
		return request(url, HttpMethod.PUT, params, TransactionAnswer.class);
	}
	
	public PayableAnswer[] retrievePayablesByTransaction(String transactionAPIId) throws PagarmeAPIException{
		String url = MessageFormat.format(ENDPOINT + TRANSACTION_PATH + PAYABLES_PATH, transactionAPIId) + "?api_key=" + APIKEY;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("api_key", APIKEY);
		return requestList(url, HttpMethod.GET, params, PayableAnswer.class);
	}
}
