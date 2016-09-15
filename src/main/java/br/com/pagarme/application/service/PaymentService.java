package br.com.pagarme.application.service;

import java.security.Principal;

import br.com.pagarme.api.answer.CardAnswer;
import br.com.pagarme.api.answer.TransactionAnswer;
import br.com.pagarme.api.exception.CancelException;
import br.com.pagarme.api.exception.CardRegistrationException;
import br.com.pagarme.api.exception.PagarmeAPIException;

public interface PaymentService {
	
	public TransactionAnswer oneTimePayment(Integer amount, String cardHash, Integer installments, String postbackUrl) throws PagarmeAPIException;
	public void cancel(String transactionAPIId) throws CancelException;
	public TransactionAnswer pay(Integer amount, Integer installments, String postbackUrl, Principal principal) throws PagarmeAPIException;
	public CardAnswer registerCard(String cardHash) throws CardRegistrationException;
}
