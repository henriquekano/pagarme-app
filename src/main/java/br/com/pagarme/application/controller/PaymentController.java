package br.com.pagarme.application.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pagarme.api.answer.TransactionAnswer;
import br.com.pagarme.api.exception.CancelException;
import br.com.pagarme.api.exception.PaymentException;
import br.com.pagarme.application.domain.dao.CustomerDAO;
import br.com.pagarme.application.domain.dao.TransactionDAO;
import br.com.pagarme.application.domain.dao.UserDAO;
import br.com.pagarme.application.domain.entity.Customer;
import br.com.pagarme.application.domain.entity.Transaction;
import br.com.pagarme.application.domain.entity.User;
import br.com.pagarme.application.service.PaymentService;
import br.com.pagarme.application.service.impl.UserManagerService;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	private final PaymentService payService;
	private final TransactionDAO transDAO;
	private final CustomerDAO customerDAO;
	private final UserDAO userDAO;
	private final UserManagerService userService;
	
	@Autowired
	public PaymentController(
			PaymentService payService, 
			TransactionDAO transDAO, 
			CustomerDAO customerDAO,
			UserDAO userDAO,
			UserManagerService userService) {
		this.payService = payService;
		this.transDAO = transDAO;
		this.customerDAO = customerDAO;
		this.userDAO = userDAO;
		this.userService = userService;
	}
	
	@RequestMapping(path="/doPay", method=RequestMethod.POST)
	public String pay(
			RedirectAttributes redirectAttrs,
			@RequestParam(name="card_hash") String cardHash,
			Principal principal){
		try {
			TransactionAnswer ans = payService.oneTimePayment(1000, cardHash, 1, "");
			Transaction transaction = new Transaction();
			transaction.setTransactionId(ans.getTid());
			transaction.setAmount(1000);
			
			Customer customer = userService.findCurrentCustomer(principal);
			
			transaction.setCustomer(customer);
			
			transDAO.save(transaction);
			
			redirectAttrs.addFlashAttribute("message", "Pagamento efetivado!");
			redirectAttrs.addFlashAttribute("success", true);
			return "redirect:/";
		} catch (PaymentException e) {
			redirectAttrs.addFlashAttribute("erros", e.getError());
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(path="/refund", method=RequestMethod.POST)
	public String refund(
			RedirectAttributes redirectAttrs,
			@RequestParam(name="transaction_id") String transactionAPIId){
		
		try{
			payService.cancel(transactionAPIId);
			transDAO.deleteByTransactionId(transactionAPIId);
			redirectAttrs.addFlashAttribute("message", "Cancelamento efetivado!");
			redirectAttrs.addFlashAttribute("success", true);
		}catch(CancelException e){
			redirectAttrs.addFlashAttribute("erros", e.getError());
		}
		return "redirect:/";
	}
}
