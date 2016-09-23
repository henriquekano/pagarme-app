package br.com.pagarme.application.service.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.pagarme.api.answer.CardAnswer;
import br.com.pagarme.api.answer.SplitRule;
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
	private final String[] recipientIds;
	
	@Autowired
	public PagarmeRestPaymentService(TransactionCommand transCommands, 
			UserManagerService userService, TransactionDAO transDAO, CardCommand cardCommands,
			@Value("${pagarme.recipientIds:}") String[] recipientIds) {
		this.transCommands = transCommands;
		this.userService = userService;
		this.transDAO = transDAO;
		this.cardCommands = cardCommands;
		this.recipientIds = recipientIds;
	}
	
	@Override
	public TransactionAnswer oneTimePayment(Integer amount, String cardHash, Integer installments, String postbackUrl) throws PagarmeAPIException{
		SplitRule[] rules = makeSplitRules(recipientIds);
		TransactionAnswer trans = this.transCommands.oneTimeTransaction(cardHash, amount, rules);
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
			SplitRule[] rules = makeSplitRules(recipientIds);
			return transCommands.pay(card.getId(), amount, currentCustomer.getName(), currentCustomer.getDocument_number(), 
					currentCustomer.getEmail(), currentCustomer.getStreet(), 
					currentCustomer.getNeighborhood(), currentCustomer.getZipcode(), 
					currentCustomer.getStreet_number(), currentCustomer.getComplementary(), 
					currentCustomer.getPhoneDdd(), currentCustomer.getPhoneNumber(), rules);
		}else{
			throw new PagarmeAPIException(null);
		}
	}

	@Override
	public CardAnswer registerCard(String cardHash) throws CardRegistrationException {
		return cardCommands.register(cardHash);
	}
	
	private SplitRule[] makeSplitRules(String[] recipientIds){
		List<SplitRule> rules = new ArrayList<>();
		
		//Controi a logica do split rules
		for (String recipientId : this.recipientIds) {
			SplitRule rule = new SplitRule();
			rule.setCharge_processing_fee(true);
			rule.setLiable(true);
			rule.setPercentage(String.valueOf(100 / this.recipientIds.length));
			rule.setRecipient_id(recipientId);
			rules.add(rule);
		}
		return rules.toArray(new SplitRule[this.recipientIds.length]);
	}

}
