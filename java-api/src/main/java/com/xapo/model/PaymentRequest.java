package com.xapo.model;

import com.google.gson.annotations.SerializedName;

public class PaymentRequest {
	
	private String to;
	private BitCoinUnit currency;
	private Double amount;
	private String subject;
	
	@SerializedName(value="timestamp")
	private long timeStamp;
	
	@SerializedName(value="unique_request_id")
	private long uniqueRequestI ;
	
	
	
	public String getTo() {
		return to;
	
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	
	

	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getSubject() {
	
		return subject;
	
	}
	
	public void setSubject(String subject) {
	
		this.subject = subject;
	
	}
	
	public long getTimeStamp() {
	
		return timeStamp;
	
	}
	
	public void setTimeStamp(long timeStamp) {
	
		this.timeStamp = timeStamp;
	
	}
	
	

	public long getUniqueRequestI() {
		return uniqueRequestI;
	}

	public void setUniqueRequestI(long uniqueRequestI) {
		this.uniqueRequestI = uniqueRequestI;
	}

	public BitCoinUnit getCurrency() {
		
		return currency;
	
	}

	public void setCurrency(BitCoinUnit currency) {
	
		this.currency = currency;
	
	} 
	
	

}
