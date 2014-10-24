package com.xapo.micro.payment.api;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import com.xapo.micro.payment.api.model.ButtonRequest;
import com.xapo.micro.payment.json.JsonMarshaller;

public class XapoMicroPaymentSDKTest {

	// pay type: "Tip", "Pay", "Deposit" o "Donate"
	private static final String APP_ID = "b91014cc28c94841";
	private static final String APP_SECRET = "c533a6e606fb62ccb13e8baf8a95cbdc";
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
		request.setReceiverUserEmail("jhon.doe@leapsight.com");
		request.setReceiverUserId("r0210");
		request.setSenderUserCellphone("+5491112341234");
		request.setSenderUserEmail("jane.doe@xapo.com");
		request.setSenderUserId("");
	}

	@Test
	public void testBuildDivWidget() {
		request.setPayType(ButtonRequest.PAY_TYPE_DONATE);
		String div = xapoMicroPaymentSDK.buildDivWidget(request);

		System.out.println((new JsonMarshaller()).getJson(request, System.currentTimeMillis()));
		System.out.println("Got:");
		System.out.println(div);
	}

	@Test
	public void testBuildIframeWidget() {
		request.setPayType(ButtonRequest.PAY_TYPE_TIP);
		String iframe = xapoMicroPaymentSDK.buildIframeWidget(request);

		System.out.println((new JsonMarshaller()).getJson(request, System.currentTimeMillis()));		
		System.out.println("Got:");
		System.out.println(iframe);
	}

}
