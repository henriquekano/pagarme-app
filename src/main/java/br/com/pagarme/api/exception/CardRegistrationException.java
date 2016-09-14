package br.com.pagarme.api.exception;

public class CardRegistrationException extends PagarmeAPIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5269489436478042167L;

	public CardRegistrationException(br.com.pagarme.api.answer.ErrorAnswer error) {
		super(error);
	}
}
