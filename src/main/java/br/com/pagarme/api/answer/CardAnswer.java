package br.com.pagarme.api.answer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CardAnswer implements Answer{

	private String object;
	private String id;
	private String date_created;
	private String date_updated;
	private String brand;
	private String holder_name;
	private String first_digits;
	private String last_digits;
	private String fingerprint;
	private String valid;
	
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getDate_updated() {
		return date_updated;
	}
	public void setDate_updated(String date_updated) {
		this.date_updated = date_updated;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getHolder_name() {
		return holder_name;
	}
	public void setHolder_name(String holder_name) {
		this.holder_name = holder_name;
	}
	public String getFirst_digits() {
		return first_digits;
	}
	public void setFirst_digits(String first_digits) {
		this.first_digits = first_digits;
	}
	public String getLast_digits() {
		return last_digits;
	}
	public void setLast_digits(String last_digits) {
		this.last_digits = last_digits;
	}
	public String getFingerprint() {
		return fingerprint;
	}
	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	
	
}
