package br.com.pagarme.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.pagarme.application.domain.entity.Customer;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping(path="", method=RequestMethod.GET)
	public String login(
			@RequestParam(name="logout", required=false) String logout,
			@RequestParam(name="signup", required=false) String signup,
			@RequestParam(name="error", required=false) String error			
			){
		if(signup != null){
			return "signup";
		}
		return "login";
	}
	
}
