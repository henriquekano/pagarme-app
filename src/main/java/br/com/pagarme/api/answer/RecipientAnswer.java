package br.com.pagarme.api.answer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipientAnswer implements Answer {

	private String object;
	private String id;
	private String transfer_enabled;
	private String last_transfer;
	private String transfer_interval;
	private String transfer_day;
	private String automatic_anticipation_enabled;
	private String anticipatable_volume_percentage;
	private String date_created;
	private String date_updated;
	private BankAccountAnswer bank_account;
	
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
	public String getTransfer_enabled() {
		return transfer_enabled;
	}
	public void setTransfer_enabled(String transfer_enabled) {
		this.transfer_enabled = transfer_enabled;
	}
	public String getLast_transfer() {
		return last_transfer;
	}
	public void setLast_transfer(String last_transfer) {
		this.last_transfer = last_transfer;
	}
	public String getTransfer_interval() {
		return transfer_interval;
	}
	public void setTransfer_interval(String transfer_interval) {
		this.transfer_interval = transfer_interval;
	}
	public String getTransfer_day() {
		return transfer_day;
	}
	public void setTransfer_day(String transfer_day) {
		this.transfer_day = transfer_day;
	}
	public String getAutomatic_anticipation_enabled() {
		return automatic_anticipation_enabled;
	}
	public void setAutomatic_anticipation_enabled(String automatic_anticipation_enabled) {
		this.automatic_anticipation_enabled = automatic_anticipation_enabled;
	}
	public String getAnticipatable_volume_percentage() {
		return anticipatable_volume_percentage;
	}
	public void setAnticipatable_volume_percentage(String anticipatable_volume_percentage) {
		this.anticipatable_volume_percentage = anticipatable_volume_percentage;
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
	public BankAccountAnswer getBank_account() {
		return bank_account;
	}
	public void setBank_account(BankAccountAnswer bank_account) {
		this.bank_account = bank_account;
	}
	
	
}
