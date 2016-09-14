package br.com.pagarme.api.exception;

public class CancelException extends PagarmeAPIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7024098691968635320L;

	public CancelException(br.com.pagarme.api.answer.ErrorAnswer error) {
		super(error);
	}
}
