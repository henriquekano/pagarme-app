package br.com.pagarme.application.service;

public interface RegistrationService {

	public static class RegistrationException extends Exception{
		/**
		 * 
		 */
		private static final long serialVersionUID = 8297194151692400174L;}
	
	public void register(String hash) throws RegistrationException;
}
