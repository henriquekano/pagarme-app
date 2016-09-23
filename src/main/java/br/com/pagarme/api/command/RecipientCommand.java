package br.com.pagarme.api.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pagarme.api.answer.RecipientAnswer;
import br.com.pagarme.api.enums.TransferIntervals;
import br.com.pagarme.api.exception.PagarmeAPIException;

public class RecipientCommand extends Command {

	private String RECIPIENTS_PATH = "/recipients";
	
	@Autowired
	public RecipientCommand(RestTemplate restTemplate, 
			@Value("${pagarme.endpoint:}") String endpoint, 
			@Value("${pagarme.apikey:}") String apikey,
			ObjectMapper objectMapper) {
		super(restTemplate, endpoint, apikey, objectMapper);
	}
	
	public RecipientAnswer createRecipient(
			TransferIntervals transferInterval, Integer transferDay, Boolean transferEnabled, 
			String bankAccountAPIId) throws PagarmeAPIException{
		String url = ENDPOINT + RECIPIENTS_PATH;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("api_key", APIKEY);
		params.add("transfer_interval", transferInterval.toString());
		params.add("transfer_day", transferDay.toString());
		params.add("transfer_enabled", transferEnabled.toString());
		params.add("bank_account_id", bankAccountAPIId.toString());
		return request(url, HttpMethod.POST, params, RecipientAnswer.class);
	}
	
	public RecipientAnswer retrieveRecipient(String recipientAPIId) throws PagarmeAPIException{
		String url = ENDPOINT + RECIPIENTS_PATH + "/" + recipientAPIId;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("api_key", APIKEY);
		return request(url, HttpMethod.GET, params, RecipientAnswer.class);
	}
	
}
