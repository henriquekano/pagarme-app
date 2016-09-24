package br.com.pagarme.application.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pagarme.api.exception.CancelException;
import br.com.pagarme.api.exception.PagarmeAPIException;
import br.com.pagarme.application.domain.dao.TransactionDAO;
import br.com.pagarme.application.domain.entity.Transaction;
import br.com.pagarme.application.service.PaymentService;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	private final PaymentService payService;
	private final TransactionDAO transDAO;
	
	@Autowired
	public PaymentController(
			PaymentService payService, 
			TransactionDAO transDAO) {
		this.payService = payService;
		this.transDAO = transDAO;
	}
	
	@RequestMapping(path="/doPay", method=RequestMethod.POST)
	public String pay(
			RedirectAttributes redirectAttrs,
			@RequestParam(name="card_hash", required = false) String cardHash,
			Principal principal){
		try {
			payService.oneTimePayment(1000, cardHash, 1, "", principal);
			
			
			redirectAttrs.addFlashAttribute("message", "Pagamento efetivado!");
			redirectAttrs.addFlashAttribute("success", true);
			return "redirect:/";
		} catch (PagarmeAPIException e) {
			redirectAttrs.addFlashAttribute("erros", e.getError());
			return "redirect:/";
		}
	}
	
	@RequestMapping(path="/doPayAutomatic", method=RequestMethod.POST)
	public String pay(
			RedirectAttributes redirectAttrs,
			Principal principal){
		try {
			payService.pay(1000, 1, "", principal);
			
			
			redirectAttrs.addFlashAttribute("message", "Pagamento efetivado!");
			redirectAttrs.addFlashAttribute("success", true);
			return "redirect:/";
		} catch (PagarmeAPIException e) {
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
			Transaction transactionCancelled = transDAO.findOneByTransactionId(transactionAPIId);
			transactionCancelled.setCancelled(true);
			transDAO.save(transactionCancelled);
			redirectAttrs.addFlashAttribute("message", "Cancelamento efetivado!");
			redirectAttrs.addFlashAttribute("success", true);
		}catch(CancelException e){
			redirectAttrs.addFlashAttribute("erros", e.getError());
		}
		return "redirect:/";
	}
}
