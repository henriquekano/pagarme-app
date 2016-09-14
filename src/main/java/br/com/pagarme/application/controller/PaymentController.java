package br.com.pagarme.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			RedirectAttributes redirectAttrs,
			@RequestParam(name="card_hash") String cardHash){
		
		try {
			payService.oneTimePayment(1000, cardHash, 1, "");
			redirectAttrs.addAttribute("success", true);
			return "redirect:/";
		} catch (PaymentException e) {
			redirectAttrs.addAttribute("erros", e.getError());
			return "redirect:/";
		}
		
	}
}
