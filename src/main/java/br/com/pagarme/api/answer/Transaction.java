package br.com.pagarme.api.answer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Transaction implements Answer{

	private String object;
	private String status;
	private String refuse_reason;
	private String status_reason;
	private String acquirer_response_code;
	private String authorization_code;
	private String soft_descriptor;
	private String tid;
	private String nsu;
	private String date_created;
	private String date_updated;
	private String amount;
	private String installments;
	private String id;
	private String cost;
	private String postback_url;
	private String payment_method;
	private String antifraud_score;
	private String boleto_url;
	private String boleto_barcode;
	private String boleto_expiration_date;
	private String referer;
	private String ip;
	private String subscription_id;
	private String phone;
	private String address;
	private String customer;
	private Card card;
	
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRefuse_reason() {
		return refuse_reason;
	}
	public void setRefuse_reason(String refuse_reason) {
		this.refuse_reason = refuse_reason;
	}
	public String getStatus_reason() {
		return status_reason;
	}
	public void setStatus_reason(String status_reason) {
		this.status_reason = status_reason;
	}
	public String getAcquirer_response_code() {
		return acquirer_response_code;
	}
	public void setAcquirer_response_code(String acquirer_response_code) {
		this.acquirer_response_code = acquirer_response_code;
	}
	public String getAuthorization_code() {
		return authorization_code;
	}
	public void setAuthorization_code(String authorization_code) {
		this.authorization_code = authorization_code;
	}
	public String getSoft_descriptor() {
		return soft_descriptor;
	}
	public void setSoft_descriptor(String soft_descriptor) {
		this.soft_descriptor = soft_descriptor;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getNsu() {
		return nsu;
	}
	public void setNsu(String nsu) {
		this.nsu = nsu;
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getInstallments() {
		return installments;
	}
	public void setInstallments(String installments) {
		this.installments = installments;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getPostback_url() {
		return postback_url;
	}
	public void setPostback_url(String postback_url) {
		this.postback_url = postback_url;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	public String getAntifraud_score() {
		return antifraud_score;
	}
	public void setAntifraud_score(String antifraud_score) {
		this.antifraud_score = antifraud_score;
	}
	public String getBoleto_url() {
		return boleto_url;
	}
	public void setBoleto_url(String boleto_url) {
		this.boleto_url = boleto_url;
	}
	public String getBoleto_barcode() {
		return boleto_barcode;
	}
	public void setBoleto_barcode(String boleto_barcode) {
		this.boleto_barcode = boleto_barcode;
	}
	public String getBoleto_expiration_date() {
		return boleto_expiration_date;
	}
	public void setBoleto_expiration_date(String boleto_expiration_date) {
		this.boleto_expiration_date = boleto_expiration_date;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSubscription_id() {
		return subscription_id;
	}
	public void setSubscription_id(String subscription_id) {
		this.subscription_id = subscription_id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	
	
}
