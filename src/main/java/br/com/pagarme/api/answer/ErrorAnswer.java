package br.com.pagarme.api.answer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ErrorAnswer implements Answer{

	private String url;
    private List<ErrorsAnswer> errors;
    private String method;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<ErrorsAnswer> getErrors() {
		return errors;
	}
	public void setErrors(List<ErrorsAnswer> errors) {
		this.errors = errors;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
    
    
}
