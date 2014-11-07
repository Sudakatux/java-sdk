package com.xapo.tools.widgets;

import org.junit.Before;
import org.junit.Test;

public class MicroPaymentTest {

    // pay type: "Tip", "Pay", "Deposit" o "Donate"
    private static final String APP_ID = "b91014cc28c94841";
    private static final String APP_SECRET = "c533a6e606fb62ccb13e8baf8a95cbdc";
    private static final String XAPO_URL = "http://dev.xapo.com:8089/pay_button/show";
    private MicroPayment mp;
    private MicroPayment mpNoTpa;
    private MicroPaymentConfig request;

    @Before
    public void setUp() throws Exception {
        mp = new MicroPayment(XAPO_URL,
                APP_ID, APP_SECRET);
        mpNoTpa = new MicroPayment(XAPO_URL);

        request = new MicroPaymentConfig();
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
        request.setPayType(MicroPaymentConfig.PAY_TYPE_DONATE);
        String div = mp.buildDivWidget(request);

        System.out.println("testBuildDivWidget -> \n" + div);

        // TODO write better assertions
        assert (div.contains("button_request"));
    }

    @Test
    public void testBuildDivWidgetmpNoTpa() {
        request.setPayType(MicroPaymentConfig.PAY_TYPE_DONATE);
        String div = mpNoTpa.buildDivWidget(request);

        System.out.println("testBuildDivWidgetmpNoTpa -> \n" + div);

        // TODO write better assertions
        assert (div.contains("payload"));
    }

    @Test
    public void testBuildIframeWidget() {
        request.setPayType(MicroPaymentConfig.PAY_TYPE_TIP);
        String iframe = mp.buildIframeWidget(request);

        System.out.println("testBuildIframeWidget -> \n" + iframe);

        assert (iframe.matches("<iframe(.*)button_request=(.*)></iframe>(.*)"));
    }

    @Test
    public void testBuildIframeWidgetNoTpa() {
        request.setPayType(MicroPaymentConfig.PAY_TYPE_TIP);
        String iframe = mpNoTpa.buildIframeWidget(request);

        System.out.println("testBuildIframeWidgetNoTpa -> \n" + iframe);

        assert (iframe.matches("<iframe(.*)payload=(.*)></iframe>(.*)"));
    }

}
