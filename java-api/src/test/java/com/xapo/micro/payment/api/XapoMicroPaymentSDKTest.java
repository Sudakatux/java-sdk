package com.xapo.micro.payment.api;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.xapo.micro.payment.api.model.ButtonRequest;

public class XapoMicroPaymentSDKTest {

	// pay type: "Tip", "Pay", "Deposit" o "Donate"
	private static final String PAY_TYPE = ButtonRequest.PAY_TYPE_DONATE;
	private static final String APP_ID = "c9f2e90b018639fa";
	private static final String APP_SECRET = "bc4e142dc053407b0028accffc289c18";
	private static final String XAPO_URL = "http://dev.xapo.com:8089/pay_button/show";
	private XapoMicroPaymentSDK xapoMicroPaymentSDK;
	private ButtonRequest request;

	@Before
	public void setUp() throws Exception {
		
		xapoMicroPaymentSDK = new XapoMicroPaymentSDK(XAPO_URL,
				APP_ID, APP_SECRET);

		request = new ButtonRequest();
		// TODO amountBIT = String  BigDecimal Decimal ??
		
		request.setAmountBIT("0.01");
		request.setPayObjectId("to0210");
		request.setReceiverUserEmail("fernando.taboada@xapo.com");
		request.setReceiverUserId("r0210");
		request.setSenderUserCellphone("+5491112341234");
		request.setSenderUserEmail("sender@xapo.com");
		request.setSenderUserId("");

		request.setPayType(PAY_TYPE);
		
	}

	@Test
	public void testBuildDivWidget() {

		String div = xapoMicroPaymentSDK.buildDivWidget(request);

		System.out.println("Got:");
		System.out.println(div);
	}

	@Test
	public void testBuildIframeWidget() {
		String iframe = xapoMicroPaymentSDK.buildIframeWidget(request);
		
		System.out.println("Got:");
		System.out.println(iframe);
	}

	@Test
	public void testEncrypt() {
		String data = "abcdefghijk";
		String encrypted = xapoMicroPaymentSDK.encrypt(data);
		assertNotEquals(encrypted, data);
	}

	@Test
	public void testBuildWidgetUrl() {
	String url = 	xapoMicroPaymentSDK.buildWidgetUrl(request);
	
	System.out.println("Got:");
	System.out.println(url);	
	
	String expectedStartURL =  XAPO_URL+"?customization=%7B%22button_text%22%3A+%22"+PAY_TYPE+"%22%7D&app_id="+APP_ID+"&button_request=";
	String urlStart = url.substring(0, expectedStartURL.length());
	assertEquals("URL start substring", expectedStartURL, urlStart);
	assertFalse("request must be encrypted", url.contains("amount_BIT"));
	
	}

}
