package br.com.pagarme.application.service;

import br.com.pagarme.api.answer.TransactionAnswer;
import br.com.pagarme.api.exception.CancelException;
import br.com.pagarme.api.exception.PaymentException;

public interface PaymentService {
	
	public TransactionAnswer oneTimePayment(Integer amount, String cardHash, Integer installments, String postbackUrl) throws PaymentException;
	public void cancel(String transactionAPIId) throws CancelException;
}
