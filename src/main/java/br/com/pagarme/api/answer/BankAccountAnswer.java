package br.com.pagarme.api.answer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BankAccountAnswer implements Answer {

	public String object;
	public String id;
	public String bank_code;
	public String agencia;
	public String agencia_dv;
	public String conta;
	public String conta_dv;
	public String document_type;
	public String document_number;
	public String legal_name;
	public String charge_transfer_fees;
	public String date_created;
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
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getAgencia_dv() {
		return agencia_dv;
	}
	public void setAgencia_dv(String agencia_dv) {
		this.agencia_dv = agencia_dv;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public String getConta_dv() {
		return conta_dv;
	}
	public void setConta_dv(String conta_dv) {
		this.conta_dv = conta_dv;
	}
	public String getDocument_type() {
		return document_type;
	}
	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}
	public String getDocument_number() {
		return document_number;
	}
	public void setDocument_number(String document_number) {
		this.document_number = document_number;
	}
	public String getLegal_name() {
		return legal_name;
	}
	public void setLegal_name(String legal_name) {
		this.legal_name = legal_name;
	}
	public String getCharge_transfer_fees() {
		return charge_transfer_fees;
	}
	public void setCharge_transfer_fees(String charge_transfer_fees) {
		this.charge_transfer_fees = charge_transfer_fees;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	
	
}
