package com.xapo.utils.json;

import com.xapo.tools.widgets.MicroPaymentConfig;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonMarshallerTest {

	private JsonMarshaller jsonMarshaller;
	@Before
	public void setUp() throws Exception {
		jsonMarshaller = new JsonMarshaller();
	}

	@Test
	public void testGetJson() {
		MicroPaymentConfig request= new MicroPaymentConfig();
	
		request.setAmountBIT("0.01");
		request.setPayObjectId("to0210");
		request.setPayType(MicroPaymentConfig.PAY_TYPE_DONATE);
		request.setReceiverUserEmail("fernando.taboada@xapo.com");
		request.setReceiverUserId("r0210");
		request.setSenderUserCellphone("+5491112341234");
		request.setSenderUserEmail("sender@xapo.com");
		request.setSenderUserId("");
		
		long timestamp = System.currentTimeMillis();
		String json = jsonMarshaller.getJson(request, timestamp);
		
		StringBuilder expected = new StringBuilder();
	
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
		expected.append("\"timestamp\":");
		expected.append(timestamp);
		expected.append("}");

		assertEquals(expected.toString(), json);
	}

}
