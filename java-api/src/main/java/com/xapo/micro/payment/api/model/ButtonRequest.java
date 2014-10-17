package com.xapo.micro.payment.api.model;

public class ButtonRequest {

	private String senderUserId="";
	private String senderUserEmail="";
	private String senderUserCellphone="";
	private String receiverUserId="";
	private String receiverUserEmail="";
	private String payObjectId="";
	// TODO amountBIT = String BigDecimal Decimal ??
	private String amountBIT="";
	private String payType="";
	private long timestamp = System.currentTimeMillis();

	public String getSenderUserId() {
		return senderUserId;
	}

	public void setSenderUserId(String senderUserId) {
		this.senderUserId = senderUserId;
	}

	public String getSenderUserEmail() {
		return senderUserEmail;
	}

	public void setSenderUserEmail(String senderUserEmail) {
		this.senderUserEmail = senderUserEmail;
	}

	public String getSenderUserCellphone() {
		return senderUserCellphone;
	}

	public void setSenderUserCellphone(String senderUserCellphone) {
		this.senderUserCellphone = senderUserCellphone;
	}

	public String getReceiverUserId() {
		return receiverUserId;
	}

	public void setReceiverUserId(String receiverUserId) {
		this.receiverUserId = receiverUserId;
	}

	public String getReceiverUserEmail() {
		return receiverUserEmail;
	}

	public void setReceiverUserEmail(String receiverUserEmail) {
		this.receiverUserEmail = receiverUserEmail;
	}

	public String getPayObjectId() {
		return payObjectId;
	}

	public void setPayObjectId(String payObjectId) {
		this.payObjectId = payObjectId;
	}

	public String getAmountBIT() {
		return amountBIT;
	}

	/**
	 * Sets the amount to send e.g.: 0.01
	 * 
	 * @param amountBIT
	 *            String number fin #.## format
	 */
	public void setAmountBIT(String amountBIT) {
		this.amountBIT = amountBIT;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public long getTimestamp() {
		return timestamp;
	}

	
}
