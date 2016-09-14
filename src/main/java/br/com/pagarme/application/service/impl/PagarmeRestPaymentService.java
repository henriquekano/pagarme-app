package br.com.pagarme.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pagarme.api.answer.TransactionAnswer;
import br.com.pagarme.api.command.TransactionCommand;
import br.com.pagarme.api.exception.CancelException;
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
	public TransactionAnswer oneTimePayment(Integer amount, String cardHash, Integer installments, String postbackUrl) throws PaymentException{
		return this.transCommands.oneTimeTransaction(cardHash, amount);
	}

	@Override
	public void cancel(String transactionAPIId) throws CancelException{
		this.transCommands.cancel(transactionAPIId);
	}

}
