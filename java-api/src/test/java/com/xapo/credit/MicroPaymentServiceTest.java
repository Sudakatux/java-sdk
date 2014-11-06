package com.xapo.credit;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.xapo.credit.impl.MicroPaymentServiceImpl;
import com.xapo.model.BitCoinUnit;
import com.xapo.model.PaymentRequest;
import com.xapo.utils.encrypt.MCrypt;
import com.xapo.utils.json.JsonMarshaller;

public class MicroPaymentServiceTest {
	
	final String emailTo="jstuartmilne@gmail.com";
	final BitCoinUnit unit = BitCoinUnit.SAT;
	final double amount=1;
	final String subject="Testing";
	final long timeStamp =new Date().getTime()/1000;
	final long uniqueRequestId =timeStamp;
	
	final String secretKey="sdfsadf";
	
	
	final String appId="sadfsadf";
	
	
	MCrypt mcrypt;
	
	JsonMarshaller jsonMarsheler;
	
	String jsonToBeEncripted = "ẗhis is json that has to be encripted";
	
	PaymentRequest pr;
	
	MicroPaymentServiceImpl microPaymentServiceImpl;
	
	@Before
	public void setUp() throws Exception{
	
		
		
		pr = new PaymentRequest();
		pr.setAmount(amount);
		pr.setTo(emailTo);
		pr.setSubject(subject);
		pr.setTimeStamp(timeStamp);
		pr.setUniqueRequestI(uniqueRequestId);
		pr.setCurrency(unit);
		
		
		mcrypt=new MCrypt();
		jsonMarsheler=new JsonMarshaller();
		
		microPaymentServiceImpl = new MicroPaymentServiceImpl(mcrypt, jsonMarsheler,secretKey,appId);
		
		
	}
	
	@Test
	public void sendPaymentTest() throws Exception{
		
		microPaymentServiceImpl.pay(pr);
		
	}
	
	
	
	
	

}
