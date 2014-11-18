package com.xapo.tools.widgets;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MicroPaymentTest {

    // pay type: "Tip", "Pay", "Deposit", "Donate" or "OAuth"
    private static final String APP_ID = "your app id";
    private static final String APP_SECRET = "your app secret";
    private static final String XAPO_URL = "https://mpayment.xapo.com/pay_button/show";
    private MicroPayment mp;
    private MicroPayment mpNoTpa;
    private MicroPaymentConfig config;
    private MicroPaymentCustomization customization;

    @Before
    public void setUp() throws Exception {
        mp = new MicroPayment(XAPO_URL,
                APP_ID, APP_SECRET);
        mpNoTpa = new MicroPayment(XAPO_URL);

        config = new MicroPaymentConfig();
        customization = new MicroPaymentCustomization();

        config.setAmountBIT("0.01");
        config.setPayObjectId("to0210");
        config.setReceiverUserEmail("jhon.doe@leapsight.com");
        config.setReceiverUserId("r0210");
        config.setSenderUserCellphone("+5491112341234");
        config.setSenderUserEmail("jane.doe@xapo.com");
        config.setSenderUserId("");
        config.setReferenceCode("test");
        config.setEndMpaymentUri("http://localhost:9000");
        config.setRedirectUri("http://localhost:9000");

        customization.setPredefinedPayValues("1,5,10");
        customization.setLoginCellphoneHeaderTitle("Test MicroPayment");
    }

    @Ignore("Set app id and secret and remove this line")
    @Test
    public void testBuildDivWidget() {
        config.setPayType(PayType.DONATE);
        customization.setButtonCss("red");
        String div = mp.buildDivWidget(config, customization);

        System.out.println("testBuildDivWidget -> \n" + div);

        // TODO write better assertions
        assert (div.contains("button_request"));
    }

    @Ignore("Set app id and secret and remove this line")
    @Test
    public void testBuildDivWidgetmpNoTpa() {
        config.setPayType(PayType.DONATE);
        customization.setButtonCss("grey");
        String div = mpNoTpa.buildDivWidget(config, customization);

        System.out.println("testBuildDivWidgetmpNoTpa -> \n" + div);

        // TODO write better assertions
        assert (div.contains("payload"));
    }

    @Ignore("Set app id and secret and remove this line")
    @Test
    public void testBuildIframeWidget() {
        config.setPayType(PayType.TIP);
        customization.setButtonCss("red");
        String iframe = mp.buildIframeWidget(config, customization);

        System.out.println("testBuildIframeWidget -> \n" + iframe);

        assert (iframe.matches("<iframe(.*)button_request=(.*)></iframe>(.*)"));
    }

    @Ignore("Set app id and secret and remove this line")
    @Test
    public void testBuildIframeWidgetNoTpa() {
        config.setPayType(PayType.TIP);
        customization.setButtonCss("grey");
        String iframe = mpNoTpa.buildIframeWidget(config, customization);

        System.out.println("testBuildIframeWidgetNoTpa -> \n" + iframe);

        assert (iframe.matches("<iframe(.*)payload=(.*)></iframe>(.*)"));
    }

}
