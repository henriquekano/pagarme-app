package br.com.pagarme.api.answer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SplitRule implements Answer {

	private String id;
	private String recipient_id;
	private Boolean liable;
	private String percentage;
	private Boolean charge_processing_fee;
	private String date_created;
	private String date_updated;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRecipient_id() {
		return recipient_id;
	}
	public void setRecipient_id(String recipient_id) {
		this.recipient_id = recipient_id;
	}
	public Boolean getLiable() {
		return liable;
	}
	public void setLiable(Boolean liable) {
		this.liable = liable;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
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
	public Boolean getCharge_processing_fee() {
		return charge_processing_fee;
	}
	public void setCharge_processing_fee(Boolean charge_processing_fee) {
		this.charge_processing_fee = charge_processing_fee;
	}
	
	
}
