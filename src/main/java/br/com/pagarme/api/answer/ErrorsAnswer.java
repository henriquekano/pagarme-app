package br.com.pagarme.api.answer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ErrorsAnswer implements Answer{

	private String type;
    private String parameter_name;
    private String message;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getParameter_name() {
		return parameter_name;
	}
	public void setParameter_name(String parameter_name) {
		this.parameter_name = parameter_name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
