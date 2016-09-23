package br.com.pagarme.api.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pagarme.api.answer.BankAccountAnswer;
import br.com.pagarme.api.exception.PagarmeAPIException;

public class BankAccountCommand extends Command {

	private final String BANK_ACCOUNT_PATH = "/bank_accounts";
	
	@Autowired
	public BankAccountCommand(RestTemplate restTemplate, 
			@Value("${pagarme.endpoint:}") String endpoint, 
			@Value("${pagarme.apikey:}") String apikey,
			ObjectMapper objectMapper) {
		super(restTemplate, endpoint, apikey, objectMapper);
	}
	
	public BankAccountAnswer create(String bankCode, String agencia, 
			String agenciaDv, String conta, String contaDv, String documentNumber, String legalName) throws PagarmeAPIException{
		String url = ENDPOINT + BANK_ACCOUNT_PATH;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("api_key", APIKEY);
		params.add("bank_code", bankCode);
		params.add("agencia", agencia);
		params.add("agencia_dv", agenciaDv);
		params.add("conta", conta);
		params.add("conta_dv", contaDv);
		params.add("document_number", documentNumber);
		params.add("legalName", legalName);
		return request(url, HttpMethod.POST, params, BankAccountAnswer.class);
	}

}
