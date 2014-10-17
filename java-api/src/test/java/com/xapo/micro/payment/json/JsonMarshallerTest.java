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
	
		request.setAmountBIT("0.01");
		request.setPayObjectId("to0210");
		request.setPayType(ButtonRequest.PAY_TYPE_DONATE);
		request.setReceiverUserEmail("fernando.taboada@xapo.com");
		request.setReceiverUserId("r0210");
		request.setSenderUserCellphone("+5491112341234");
		request.setSenderUserEmail("sender@xapo.com");
		request.setSenderUserId("");
		
		long timestamp = System.currentTimeMillis();
		String json = jsonMarshaller.getJson(request, timestamp);
		
		StringBuilder expected = new StringBuilder();
//		expected.append("{\"request\":");
	
		expected.append("{\"sender_user_id\":\""+request.getSenderUserId()+"\"");
		expected.append(",");
		expected.append("\"sender_user_email\":\""+request.getSenderUserEmail()+"\"");
		expected.append(",");
		expected.append("\"sender_user_cellphone\":\""+request.getSenderUserCellphone()+"\"");
		expected.append(",");
		expected.append("\"receiver_user_id\":\""+request.getReceiverUserId()+"\"");
		expected.append(",");
		expected.append("\"receiver_user_email\":\""+request.getReceiverUserEmail()+"\"");
		expected.append(",");
		expected.append("\"pay_object_id\":\""+request.getPayObjectId()+"\"");
		expected.append(",");
		expected.append("\"amount_BIT\":"+request.getAmountBIT()+"");
		expected.append(",");
//		expected.append("\"pay_type\":\"aPayType\"");
//		expected.append(",");
		expected.append("\"timestamp\":");
		expected.append(timestamp);
		expected.append("}");
//		expected.append("}");
		
		assertEquals(expected.toString(), json);
		
	}

}
