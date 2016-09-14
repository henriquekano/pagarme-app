package br.com.pagarme.application.controller;

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

@Controller
@RequestMapping("")
public class HomeController {

	private final TransactionDAO transDAO;
	private final CustomerDAO customerDAO;
	
	@Autowired
	public HomeController(TransactionDAO transDAO, CustomerDAO customerDAO) {
		this.transDAO = transDAO;
		this.customerDAO = customerDAO;
	}
	
	@RequestMapping("/")
	public String index(Model model){
		Iterable<Transaction> transactions = transDAO.findAll();
		model.addAttribute("transactions", transactions);
		return "index";
	}
	
	@RequestMapping(path="/register", method=RequestMethod.POST)
	public String register(
			@ModelAttribute(name="customer") Customer customer			
			){
		customerDAO.save(customer);
		return "login";
	}
	
}
