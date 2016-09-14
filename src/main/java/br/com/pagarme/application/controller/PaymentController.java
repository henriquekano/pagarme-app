package br.com.pagarme.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.pagarme.api.exception.PaymentException;
import br.com.pagarme.application.service.PaymentService;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	private final PaymentService payService;
	
	@Autowired
	public PaymentController(PaymentService payService) {
		this.payService = payService;
	}
	
	@RequestMapping("/doPay")
	public String pay(
			Model model,
			@RequestParam(name="card_hash") String cardHash){
		
		try {
			payService.oneTimePayment(0, cardHash, 1, "");
			return "ok";
		} catch (PaymentException e) {
			model.addAttribute("erro", e);
			return "erro";
		}
		
	}
}
