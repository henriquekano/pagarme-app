package br.com.pagarme.application.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.pagarme.application.domain.dao.CustomerDAO;
import br.com.pagarme.application.domain.dao.TransactionDAO;
import br.com.pagarme.application.domain.entity.Customer;
import br.com.pagarme.application.domain.entity.Transaction;
import br.com.pagarme.application.service.impl.UserManagerService;

@Controller
@RequestMapping("")
public class HomeController {

	private final TransactionDAO transDAO;
	private final UserManagerService userService;
	private final CustomerDAO customerDAO;
	
	@Autowired
	public HomeController(TransactionDAO transDAO, UserManagerService userService, CustomerDAO customerDAO) {
		this.transDAO = transDAO;
		this.userService = userService;
		this.customerDAO = customerDAO;
	}
	
	@RequestMapping("/")
	public String index(Model model, Principal principal){
		
		Customer customer = userService.findCurrentCustomer(principal);
		
		Iterable<Transaction> transactions = transDAO.findByCustomer(customer);
		model.addAttribute("transactions", transactions);
		return "index";
	}
	
	@RequestMapping(path="/register", method=RequestMethod.POST)
	public String register(
			@ModelAttribute(name="customer") Customer customer			
			){
		customerDAO.save(customer);
		Iterable<Customer> a = customerDAO.findAll();
		return "login";
	}
	
}
