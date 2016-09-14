package br.com.pagarme.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pagarme.api.answer.Answer;
import br.com.pagarme.api.command.TransactionCommand;
import br.com.pagarme.api.exception.PaymentException;
import br.com.pagarme.application.service.PaymentService;



@Service
public class PagarmeRestPaymentService implements PaymentService {

	private final TransactionCommand transCommands;
	
	@Autowired
	public PagarmeRestPaymentService(TransactionCommand transCommands) {
		this.transCommands = transCommands;
	}
	
	@Override
	public Answer oneTimePayment(Integer amount, String cardHash, Integer installments, String postbackUrl) throws PaymentException{
		return (Answer) this.transCommands.oneTimeTransaction(cardHash, amount);
	}

	@Override
	public Answer cancel() {
		return null;
	}

}
