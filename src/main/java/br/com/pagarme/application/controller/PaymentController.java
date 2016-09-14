package br.com.pagarme.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pagarme.api.answer.TransactionAnswer;
import br.com.pagarme.api.exception.CancelException;
import br.com.pagarme.api.exception.PaymentException;
import br.com.pagarme.application.domain.dao.TransactionDAO;
import br.com.pagarme.application.domain.entity.Transaction;
import br.com.pagarme.application.service.PaymentService;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	private final PaymentService payService;
	private final TransactionDAO transDAO;
	
	@Autowired
	public PaymentController(PaymentService payService, TransactionDAO transDAO) {
		this.payService = payService;
		this.transDAO = transDAO;
	}
	
	@RequestMapping(path="/doPay", method=RequestMethod.POST)
	public String pay(
			RedirectAttributes redirectAttrs,
			@RequestParam(name="card_hash") String cardHash){
		try {
			TransactionAnswer ans = payService.oneTimePayment(1000, cardHash, 1, "");
			Transaction transaction = new Transaction();
			transaction.setTransactionId(ans.getTid());
			transDAO.save(transaction);
			
			redirectAttrs.addAttribute("message", "Pagamento efetivado!");
			return "redirect:/";
		} catch (PaymentException e) {
			redirectAttrs.addAttribute("erros", e.getError());
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(path="/refund", method=RequestMethod.POST)
	public String refund(
			RedirectAttributes redirectAttrs,
			@RequestParam(name="transaction_id") String transactionAPIId){
		
		try{
			payService.cancel(transactionAPIId);
			redirectAttrs.addAttribute("message", "Cancelamento efetivado!");
		}catch(CancelException e){
			redirectAttrs.addAttribute("erros", e.getError());
		}
		return "redirect:/";
	}
}
