package br.com.pagarme.application.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pagarme.api.answer.CardAnswer;
import br.com.pagarme.api.answer.PayableAnswer;
import br.com.pagarme.api.exception.PagarmeAPIException;
import br.com.pagarme.application.domain.dao.CustomerDAO;
import br.com.pagarme.application.domain.dao.TransactionDAO;
import br.com.pagarme.application.domain.entity.Customer;
import br.com.pagarme.application.domain.entity.Transaction;
import br.com.pagarme.application.service.impl.PagarmeRestPaymentService;
import br.com.pagarme.application.service.impl.UserManagerService;

@Controller
@RequestMapping("")
public class HomeController {

	private final TransactionDAO transDAO;
	private final UserManagerService userService;
	private final CustomerDAO customerDAO;
	private final PagarmeRestPaymentService pagarmeService;
	
	@Autowired
	public HomeController(TransactionDAO transDAO, UserManagerService userService, CustomerDAO customerDAO,
			PagarmeRestPaymentService pagarmeService) {
		this.transDAO = transDAO;
		this.userService = userService;
		this.customerDAO = customerDAO;
		this.pagarmeService = pagarmeService;
	}
	
	@RequestMapping("/")
	public String index(Model model, Principal principal){
		
		Customer customer = userService.findCurrentCustomer(principal);
		
		Iterable<Transaction> transactions = transDAO.findByCustomerAndCancelled(customer, false);
		List<PayableAnswer> payables = new ArrayList<>();
		try{
			for (Transaction transaction : transactions) {
				PayableAnswer[] existingPayables = pagarmeService.getPayables(transaction);
				payables.addAll(Arrays.asList(existingPayables));
			}
			
			model.addAttribute("payables", payables);
			model.addAttribute("transactions", transactions);
			model.addAttribute("currentUser", userService.findCurrentCustomer(principal));
		}catch(PagarmeAPIException e){
			model.addAttribute("erros", e.getError());
		}
		return "index";
	}
	
	@RequestMapping(path="/register", method=RequestMethod.POST)
	public String register(
			@ModelAttribute(name="customer") Customer customer			
			){
		try{
			customerDAO.save(customer);
		}catch(Exception e){
			return "signup";
		}
		
		return "login";
	}
	
	@RequestMapping(path="/registerCard", method=RequestMethod.POST)
	public String pay(
			RedirectAttributes redirectAttrs,
			Principal principal,
			@RequestParam("card_hash") String cardHash){
		try {
			CardAnswer card = pagarmeService.registerCard(cardHash);
			Customer customer = userService.findCurrentCustomer(principal);
			customer.setCardRegistered(true);
			customer.setCardId(card.getId());
			customerDAO.save(customer);
			
			redirectAttrs.addFlashAttribute("message", "Registro Feito!");
			redirectAttrs.addFlashAttribute("success", true);
			return "redirect:/";
		} catch (PagarmeAPIException e) {
			redirectAttrs.addFlashAttribute("erros", e.getError());
			return "redirect:/";
		}
	}
	
}
