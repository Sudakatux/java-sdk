package com.xapo.utils.json;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.xapo.model.BitCoinUnit;
import com.xapo.model.PaymentRequest;
import com.xapo.tools.widgets.MicroPaymentConfig;

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
	
	
	@Test
	public void shouldGeneratepaymentPayload(){
		
		final String emailTo="ezecuesta@gmail.com";
		final BitCoinUnit unit = BitCoinUnit.BTC;
		final double amount=0.00000001;
		final String subject="Take your Free Bitcoins Now, BitOnPlay.com";
		final long timeStamp = 1415191371;
		final long uniqueRequestId =1415191371;
		
		PaymentRequest pr = new PaymentRequest();
		pr.setAmount(amount);
		pr.setTo(emailTo);
		pr.setSubject(subject);
		pr.setTimeStamp(timeStamp);
		pr.setUniqueRequestI(uniqueRequestId);
		pr.setCurrency(unit);
		
		String json = jsonMarshaller.paymentToJson(pr);
		System.out.println(json);
		
		StringBuilder expected = new StringBuilder();
		
		expected.append("{\"to\":\""+emailTo+"\"");
		expected.append(",");
		expected.append("\"currency\":\""+unit.toString()+"\"");
		expected.append(",");
		expected.append("\"amount\":"+amount+"");
		expected.append(",");
		expected.append("\"subject\":\""+subject+"\"");
		expected.append(",");
		expected.append("\"timestamp\":"+timeStamp+"");
		expected.append(",");
		expected.append("\"unique_request_id\":"+uniqueRequestId+"");
		expected.append("}");
		
		assertEquals(expected.toString(), json);
		
	}
	
	

}
