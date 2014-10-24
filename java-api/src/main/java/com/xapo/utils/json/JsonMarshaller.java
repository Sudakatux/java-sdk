package com.xapo.utils.json;

import com.xapo.tools.widgets.MicroPaymentConfig;

public class JsonMarshaller {

	public String getJson(MicroPaymentConfig microPaymentConfig, long timestamp) {

		StringBuilder json = new StringBuilder();

		//json.append("{\"request\":{");
		json.append("{");
		json.append("\"sender_user_id\":\"");
		json.append(microPaymentConfig.getSenderUserId());
		json.append("\",\"sender_user_email\":\"");
		json.append(microPaymentConfig.getSenderUserEmail());
		json.append("\",\"sender_user_cellphone\":\"");
		json.append(microPaymentConfig.getSenderUserCellphone());
		json.append("\",\"receiver_user_id\":\"");
		json.append(microPaymentConfig.getReceiverUserId());
		json.append("\",\"receiver_user_email\":\"");
		json.append(microPaymentConfig.getReceiverUserEmail());
		json.append("\",\"pay_object_id\":\"");
		json.append(microPaymentConfig.getPayObjectId());
		json.append("\",\"amount_BIT\":");
		json.append(microPaymentConfig.getAmountBIT());
//		json.append(",\"pay_type\":\"");
//		json.append(buttonRequest.getPayType());
//		json.append("\"");
		json.append(",\"timestamp\":");
		json.append(timestamp);
		json.append("}");
//		json.append("}");

		return json.toString();
	}

	
	
// XStream was removed to minimize dependencies for the API	
//	private XStream getXstream(){
//		if (xstream == null){
//
//			XStream newInstance = new XStream(new JettisonMappedXmlDriver());
//			newInstance.alias("request", ButtonRequest.class);
//			newInstance.aliasField("sender_user_id", ButtonRequest.class,"senderUserId");
//			newInstance.aliasField("sender_user_email", ButtonRequest.class,"senderUserEmail");
//			newInstance.aliasField("sender_user_cellphone", ButtonRequest.class,"senderUserCellphone");
//			newInstance.aliasField("receiver_user_id", ButtonRequest.class,"receiverUserId");
//			newInstance.aliasField("receiver_user_email", ButtonRequest.class,"receiverUserEmail");
//			newInstance.aliasField("pay_object_id", ButtonRequest.class,"payObjectId");
//			newInstance.aliasField("amount_BIT", ButtonRequest.class,"amountBIT");
//			newInstance.aliasField("pay_type", ButtonRequest.class,"payType");
//			xstream = newInstance;
//		}
//		return xstream;
//	}
	
}
