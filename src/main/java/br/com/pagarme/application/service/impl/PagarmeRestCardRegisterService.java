package br.com.pagarme.application.service.impl;

import br.com.pagarme.application.service.RegistrationService;
import me.pagar.model.Card;
import me.pagar.model.PagarMeException;

public class PagarmeRestCardRegisterService implements RegistrationService {

	@Override
	public void register(String hash) throws RegistrationException {
		Card card = new Card();
		card.setHash(hash);
		try{
			card.save();
		}catch(PagarMeException e){
			throw new RegistrationService.RegistrationException();
		}
	}

}
