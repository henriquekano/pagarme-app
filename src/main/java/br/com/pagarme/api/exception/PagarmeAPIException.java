package br.com.pagarme.api.exception;

public class PagarmeAPIException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5260648602081415012L;
	private br.com.pagarme.api.answer.ErrorAnswer error;
	
	public PagarmeAPIException(br.com.pagarme.api.answer.ErrorAnswer error){
		super();
		this.error = error;
	}
	
	public br.com.pagarme.api.answer.ErrorAnswer getError() {
		return this.error;
	}
}
