package br.com.pagarme.api;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pagarme.api.command.TransactionCommand;
import br.com.pagarme.api.exception.CancelException;
import br.com.pagarme.api.exception.PaymentException;
import br.com.pagarme.application.ApplicationApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT, classes={ApplicationApplication.class})
public class ApiCommandsTests {

	private static TestRestTemplate testRestTemplate = new TestRestTemplate();
	@Autowired
	private static ObjectMapper objectMapper;
	private static final String ENDPOINT = "ENDPOINT";
	private static final String APIKEY = "1234";
	
	private static  MockRestServiceServer mockServer;
	private static TransactionCommand transCommands;
	
	@BeforeClass
	public static void setup(){
		transCommands = new TransactionCommand(testRestTemplate.getRestTemplate(), ENDPOINT, APIKEY, objectMapper);
	}
	
	@Before
	public void setupEach(){
		mockServer = MockRestServiceServer.createServer(testRestTemplate.getRestTemplate());
	}
	
	@Test
	public void testPaymentCallRequest() throws PaymentException{
		String cardHash = "CARDHASH";
		Integer amount = 1000;
		
		mockServer
			.expect(requestTo("ENDPOINT/transactions"))//tem que ir pro /transactions
			.andExpect(method(HttpMethod.POST))//tem que ser POST
			.andExpect(content().string("api_key=" + APIKEY + "&amount=" + amount + "&card_hash=" + cardHash))//Tem que mandar em url encoded
			.andRespond(MockRestResponseCreators.withSuccess("{}", MediaType.APPLICATION_JSON));//responda algo
		transCommands.oneTimeTransaction(cardHash, 1000);
		mockServer.verify();
	}
	
	@Test
	public void testCancelCallRequest() throws CancelException{
		String transactionId = "0987";
		
		mockServer
			.expect(requestTo("ENDPOINT/transactions/" + transactionId + "/refund"))//tem que ir pro /transactions
			.andExpect(method(HttpMethod.POST))//tem que ser POST
			.andExpect(content().string("api_key=1234"))//Tem que mandar em url encoded
			.andRespond(MockRestResponseCreators.withSuccess("{}", MediaType.APPLICATION_JSON));//responda algo
		transCommands.cancel(transactionId);
		mockServer.verify();
	}
}
