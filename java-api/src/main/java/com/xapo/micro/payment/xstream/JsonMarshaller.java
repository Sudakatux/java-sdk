package com.xapo.micro.payment.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.xapo.micro.payment.api.model.ButtonRequest;

public class JsonMarshaller {

	private XStream xstream;
	
	private XStream getXstream(){
		if (xstream == null){

			XStream newInstance = new XStream(new JettisonMappedXmlDriver());
			newInstance.alias("request", ButtonRequest.class);
			newInstance.aliasField("sender_user_id", ButtonRequest.class,"senderUserId");
			newInstance.aliasField("sender_user_email", ButtonRequest.class,"senderUserEmail");
			newInstance.aliasField("sender_user_cellphone", ButtonRequest.class,"senderUserCellphone");
			newInstance.aliasField("receiver_user_id", ButtonRequest.class,"receiverUserId");
			newInstance.aliasField("receiver_user_email", ButtonRequest.class,"receiverUserEmail");
			newInstance.aliasField("pay_object_id", ButtonRequest.class,"payObjectId");
			newInstance.aliasField("amount_BIT", ButtonRequest.class,"amountBIT");
			newInstance.aliasField("pay_type", ButtonRequest.class,"payType");
			xstream = newInstance;
		}
		return xstream;
	}
	
	
	public String getJson(ButtonRequest buttonRequest) {
		return	getXstream().toXML(buttonRequest);
	}
	
	
	
}
