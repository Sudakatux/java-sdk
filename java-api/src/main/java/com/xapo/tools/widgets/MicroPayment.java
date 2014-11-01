package com.xapo.tools.widgets;

import com.xapo.utils.encrypt.MCrypt;
import com.xapo.utils.json.JsonMarshaller;

import java.net.URI;

public class MicroPayment {

    private ServiceParameters serviceParameters;
    private String appID;
    private String appSecret;
    private JsonMarshaller jsonMarshaller = new JsonMarshaller();
    private MCrypt aesEncrypt = new MCrypt();

    /**
     * Constructor. Configure a payment button widget with TPA credentials.
     *
     * @param serviceURL The service URL. e.g.
     *                   "http://dev.xapo.com:8089/pay_button/show"
     * @param appID      The ID of the application that uses this SDK
     * @param appSecret  the encryption secret key of the application that uses this
     *                   SDK
     */
    public MicroPayment(String serviceURL, String appID, String appSecret) {
        this.appID = appID;
        this.appSecret = appSecret;
        this.serviceParameters = new ServiceParameters(serviceURL);
    }

    /**
     * Constructor. Configure a payment button without a TPA.
     *
     * @param serviceURL The service URL. e.g.
     *                   "http://dev.xapo.com:8089/pay_button/show"
     */
    public MicroPayment(String serviceURL) {
        this.serviceParameters = new ServiceParameters(serviceURL);
    }


    /**
     * Encrypts the data to be sent to the server
     *
     * @param data the String to be encrypted
     * @return the encrypted data
     */
    protected String encrypt(String data) {
        try {
            return aesEncrypt.encrypt(appSecret, data);
        } catch (Exception e) {
            throw new RuntimeException("Can't encrypt the data", e);

        }
    }

    /**
     * Build the URL based in the request data
     *
     * @param request the request to be sent to the server
     * @return the URL to send the request data
     */
    protected String buildWidgetUrl(MicroPaymentConfig request) {

        long timestamp = System.currentTimeMillis();
        String buttonRequestJson = jsonMarshaller.getJson(request, timestamp);
        URI widgetUrl = null;
        String widgetStr = "";

        StringBuilder query = new StringBuilder();
        query.append("customization={\"button_text\":\"");
        query.append(request.getPayType());
        query.append("\"}");

        if (this.appID == null || this.appSecret == null) {
            query.append("&");
            query.append("payload=");
            query.append(buttonRequestJson);
        } else {
            String buttonRequestEnc = encrypt(buttonRequestJson);

            query.append("&");
            query.append("app_id=");
            query.append(appID);

            query.append("&");
            query.append("button_request=");
            buttonRequestEnc = buttonRequestEnc.replace("=", "%3D");
            query.append(buttonRequestEnc);
        }

        String queryStr = query.toString();

        queryStr = queryStr.replace("/", "%2F");
        queryStr = queryStr.replace("\"", "%22");
        queryStr = queryStr.replace("{", "%7B");
        queryStr = queryStr.replace("}", "%7D");
        queryStr = queryStr.replace(" ", "%20");
        queryStr = queryStr.replace("+", "%2B");
        queryStr = queryStr.replace(":", "%3A");
        queryStr = queryStr.replace("\n", "%0A");

        widgetStr = serviceParameters.getScheme() + "://" +
                serviceParameters.getHost() + ":" + serviceParameters.getPort() +
                serviceParameters.getPath() + "?" + queryStr;

        // fix : encode
        return widgetStr;
    }

    protected URI createURI(String query) {
        try {
            return new URI(serviceParameters.getScheme(), null /*userInfo*/,
                    serviceParameters.getHost(), serviceParameters.getPort(), serviceParameters.getPath(), query, null /* fragment */);

        } catch (Exception e) {
            throw new RuntimeException("Can't create URL", e);
        }

    }

    /**
     * Builds a Div HTML tag including the request data
     *
     * @param microPaymentConfig the data to sent to the server
     * @return the HTML tag string
     */
    public String buildDivWidget(MicroPaymentConfig microPaymentConfig) {
        String widgetUrl = buildWidgetUrl(microPaymentConfig);
        StringBuffer res = new StringBuffer();
        res.append("<div id='tipButtonDiv' class='tipButtonDiv'></div>\n");
        res.append("<div id='tipButtonPopup' class='tipButtonPopup'></div>\n");
        res.append("<script>\n");
        res.append("$(document).ready(function() {");
        res.append("$('#tipButtonDiv').load('");
        res.append(widgetUrl);
        res.append("');");
        res.append("});\n");
        res.append("</script>");

        return res.toString();
    }

    /**
     * Builds an iFrame HTML tag including the request data
     *
     * @param microPaymentConfig the data to configure de payment button
     * @return the HTML tag string
     */
    public String buildIframeWidget(MicroPaymentConfig microPaymentConfig) {

        String widgetUrl = buildWidgetUrl(microPaymentConfig);
        StringBuffer res = new StringBuffer();
        res.append("<iframe id='tipButtonFrame' scrolling='no' frameborder='0' style='border:none; overflow:hidden; height:22px;' allowTransparency='true' src='");

        res.append(widgetUrl);
        res.append("'></iframe>");
        return res.toString();
    }

}
