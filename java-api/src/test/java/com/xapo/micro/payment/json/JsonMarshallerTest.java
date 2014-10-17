package com.xapo.micro.payment.json;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.xapo.micro.payment.api.model.ButtonRequest;

public class JsonMarshallerTest {

	private JsonMarshaller jsonMarshaller;
	@Before
	public void setUp() throws Exception {
		jsonMarshaller = new JsonMarshaller();
	}

	@Test
	public void testGetJson() {
		ButtonRequest request= new ButtonRequest();
		// TODO amountBIT = String  BigDecimal Decimal ??
		request.setAmountBIT("0.01");
		request.setPayObjectId("aPayObjectId");
		request.setPayType("aPayType");
		request.setReceiverUserEmail("receiver@email.com");
		request.setReceiverUserId("receiverUserId");
		request.setSenderUserCellphone("123456789");
		request.setSenderUserEmail("sender@email.com");
		request.setSenderUserId("aSenderId");
		
		String json = jsonMarshaller.getJson(request);
		
		StringBuilder expected = new StringBuilder();
//		expected.append("{\"request\":");
	
		expected.append("{\"sender_user_id\":\"aSenderId\"");
		expected.append(",");
		expected.append("\"sender_user_email\":\"sender@email.com\"");
		expected.append(",");
		expected.append("\"sender_user_cellphone\":\"123456789\"");
		expected.append(",");
		expected.append("\"receiver_user_id\":\"receiverUserId\"");
		expected.append(",");
		expected.append("\"receiver_user_email\":\"receiver@email.com\"");
		expected.append(",");
		expected.append("\"pay_object_id\":\"aPayObjectId\"");
		expected.append(",");
		expected.append("\"amount_BIT\":0.01");
		expected.append(",");
//		expected.append("\"pay_type\":\"aPayType\"");
//		expected.append(",");
		expected.append("\"timestamp\":");
		expected.append(request.getTimestamp());
		expected.append("}");
//		expected.append("}");
		
		assertEquals(expected.toString(), json);
		
	}

}
