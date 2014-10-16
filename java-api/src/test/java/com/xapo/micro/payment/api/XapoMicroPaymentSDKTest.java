package com.xapo.micro.payment.api;

import static org.junit.Assert.*;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import org.junit.Before;
import org.junit.Test;

import com.xapo.micro.payment.api.model.ButtonRequest;

public class XapoMicroPaymentSDKTest {

	private static final String PAY_TYPE = "Donate";
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
		request.setAmountBIT("0");
		request.setPayObjectId("aPayObjectId");
		request.setReceiverUserEmail("receiver@email.com");
		request.setReceiverUserId("receiverUserId");
		request.setSenderUserCellphone("123456789");
		request.setSenderUserEmail("sender@email.com");
		request.setSenderUserId("aSenderId");

		// pay type: "Tip", "Pay", "Deposit" o "Donate"
		request.setPayType(PAY_TYPE);
		
	}

	@Test
	public void testBuildDivWidget() {

		String div = xapoMicroPaymentSDK.buildDivWidget(request);

		//TODO fail("Verificar si esta bien el Div");
		System.err.println("Verificar si esta bien el Div");
		System.err.println(div);
	}

	@Test
	public void testBuildIframeWidget() {
		String iframe = xapoMicroPaymentSDK.buildIframeWidget(request);
		
		//TODO fail("Verificar si esta bien el IFrame");
		System.err.println("Verificar si esta bien el IFrame");
		System.err.println(iframe);
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
//	System.out.println(url);

	String colonStrPython = "%3A+";
	String colonStrJava = ":";
	System.err.println("verificar el encoding de :");
	
	
	String expectedStartURL =  XAPO_URL+"?customization=%7B%22button_text%22"+colonStrJava+"%22"+PAY_TYPE+"%22%7D&app_id="+APP_ID+"&button_request=";
	String urlStart = url.substring(0, expectedStartURL.length());
	assertEquals("URL start substring", expectedStartURL, urlStart);
	// the encrypted json is in the tail of this URL 
	
	}

}
