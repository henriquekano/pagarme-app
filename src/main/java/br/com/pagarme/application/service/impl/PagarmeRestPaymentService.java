package br.com.pagarme.application.service.impl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pagarme.api.answer.CardAnswer;
import br.com.pagarme.api.answer.TransactionAnswer;
import br.com.pagarme.api.command.CardCommand;
import br.com.pagarme.api.command.TransactionCommand;
import br.com.pagarme.api.exception.CancelException;
import br.com.pagarme.api.exception.CardRegistrationException;
import br.com.pagarme.api.exception.PagarmeAPIException;
import br.com.pagarme.application.domain.dao.TransactionDAO;
import br.com.pagarme.application.domain.entity.Customer;
import br.com.pagarme.application.service.PaymentService;



@Service
public class PagarmeRestPaymentService implements PaymentService {

	private final TransactionCommand transCommands;
	private final CardCommand cardCommands;
	private final UserManagerService userService;
	private final TransactionDAO transDAO;
	
	@Autowired
	public PagarmeRestPaymentService(TransactionCommand transCommands, 
			UserManagerService userService, TransactionDAO transDAO, CardCommand cardCommands) {
		this.transCommands = transCommands;
		this.userService = userService;
		this.transDAO = transDAO;
		this.cardCommands = cardCommands;
	}
	
	@Override
	public TransactionAnswer oneTimePayment(Integer amount, String cardHash, Integer installments, String postbackUrl) throws PagarmeAPIException{
		TransactionAnswer trans = this.transCommands.oneTimeTransaction(cardHash, amount);
		return trans;
	}
	
	

	@Override
	public void cancel(String transactionAPIId) throws CancelException{
		this.transCommands.cancel(transactionAPIId);
	}

	@Override
	public TransactionAnswer pay(Integer amount, Integer installments, String postbackUrl, Principal principal)
			throws PagarmeAPIException {
		
		Customer currentCustomer = userService.findCurrentCustomer(principal);
		if(currentCustomer.getCardId() != null){
			CardAnswer card = cardCommands.retrieve(currentCustomer.getCardId());
			return transCommands.pay(card.getId(), amount);
		}else{
			throw new PagarmeAPIException(null);
		}
	}

	@Override
	public CardAnswer registerCard(String cardHash) throws CardRegistrationException {
		return cardCommands.register(cardHash);
	}
	
	

}
