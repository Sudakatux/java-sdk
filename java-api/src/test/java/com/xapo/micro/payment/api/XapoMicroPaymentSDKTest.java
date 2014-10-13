package com.xapo.micro.payment.api;

import org.junit.Assert;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.xapo.micro.payment.api.model.ButtonRequest;

public class XapoMicroPaymentSDKTest {

	private static final String APP_ID = "appID";
	private XapoMicroPaymentSDK xapoMicroPaymentSDK;
	private ButtonRequest request;

	@Before
	public void setUp() throws Exception {
		String appSecret = "theBestSecretKey";
		xapoMicroPaymentSDK = new XapoMicroPaymentSDK("https", "xapo.com",
				APP_ID, appSecret);

		request = new ButtonRequest();
		// TODO amountBIT = String  BigDecimal Decimal ??
		request.setAmountBIT("0");
		request.setPayObjectId("aPayObjectId");
		request.setPayType("aPayType");
		request.setReceiverUserEmail("receiver@email.com");
		request.setReceiverUserId("receiverUserId");
		request.setSenderUserCellphone("123456789");
		request.setSenderUserEmail("sender@email.com");
		request.setSenderUserId("aSenderId");

	}

	@Test
	public void testBuildDivWidget() {

		String div = xapoMicroPaymentSDK.buildDivWidget(request);
		System.out.println(div);
		fail("Verificar si esta bien el Div");
	}

	@Test
	public void testBuildIframeWidget() {
		String iframe = xapoMicroPaymentSDK.buildIframeWidget(request);
		System.err.println(iframe);
		fail("Verificar si esta bien el IFrame");
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
	System.out.println(url);
	
	assertTrue(url.startsWith("https://xapo.com/app_id="+APP_ID+"&button_request="));
	// the encrypted json is in the miidle of this URL 
	assertTrue(url.endsWith("&customization=%7Bbutton_text:%20"+request.getPayType()+"%7D"));
	
	}

}
