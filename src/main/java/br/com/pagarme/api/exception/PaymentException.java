package br.com.pagarme.api.exception;

public class PaymentException extends PagarmeAPIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3080081751743622856L;

	public PaymentException(br.com.pagarme.api.answer.ErrorAnswer error) {
		super(error);
	}
}
