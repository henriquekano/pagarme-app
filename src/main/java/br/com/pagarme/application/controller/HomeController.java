package br.com.pagarme.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.pagarme.application.domain.dao.TransactionDAO;
import br.com.pagarme.application.domain.entity.Transaction;

@Controller
public class HomeController {

	private final TransactionDAO transDAO;
	
	@Autowired
	public HomeController(TransactionDAO transDAO) {
		this.transDAO = transDAO;
	}
	
	@RequestMapping("/")
	public String index(Model model){
		Iterable<Transaction> transactions = transDAO.findAll();
		model.addAttribute("transactions", transactions);
		return "index";
	}
}
