package br.com.pagarme.application.service;

import br.com.pagarme.api.answer.Answer;
import br.com.pagarme.api.exception.PaymentException;

public interface PaymentService {
	
	public Answer oneTimePayment(Integer amount, String cardHash, Integer installments, String postbackUrl) throws PaymentException;
	public Answer cancel() throws PaymentException;
}
