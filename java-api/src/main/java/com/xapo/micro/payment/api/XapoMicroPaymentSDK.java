package com.xapo.micro.payment.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;

import com.xapo.micro.payment.api.model.ButtonRequest;
import com.xapo.micro.payment.encrypt.MCrypt;
import com.xapo.micro.payment.json.JsonMarshaller;

public class XapoMicroPaymentSDK {

	private ServiceParameters serviceParameters;
	private String appID;
	private String appSecret;
	private JsonMarshaller jsonMarshaller = new JsonMarshaller();
	private MCrypt aesEncrypt = new MCrypt();

	/**
	 * Constructor
	 * 
	 * @param serviceURL
	 *            The service URL. e.g.
	 *            "http://dev.xapo.com:8089/pay_button/show"
	 * @param appID
	 *            The ID of the application that uses this SDK
	 * @param appSecret
	 *            the encryption secret key of the application that uses this
	 *            SDK
	 */
	public XapoMicroPaymentSDK(String serviceURL, String appID, String appSecret) {

		this.appID = appID;
		this.appSecret = appSecret;
		this.serviceParameters = new ServiceParameters(serviceURL);

	}

	/**
	 * Encrypts the data to be sent to the server
	 * 
	 * @param data
	 *            the String to be encrypted
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
	 * @param request
	 *            the request to be sent to the server
	 * @return the URL to send the request data
	 */
	protected String buildWidgetUrl(ButtonRequest request) {
		
		long timestamp = System.currentTimeMillis();
		String buttonRequestJson = jsonMarshaller.getJson(request, timestamp);
		String buttonRequestEnc = encrypt(buttonRequestJson);
		URI widgetUrl = null;
		String widgetStr = "";

		StringBuilder query = new StringBuilder();
		query.append("customization={\"button_text\":\"");
		query.append(request.getPayType());
		query.append("\"}");

		query.append("&");
		query.append("app_id=");
		query.append(appID);

		query.append("&");
		query.append("button_request=");
		buttonRequestEnc = buttonRequestEnc.replace("=", "%3D");
		query.append(buttonRequestEnc);

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
	 * @param buttonRequest
	 *            the data to sent to the server
	 * @return the HTML tag string
	 */
	public String buildDivWidget(ButtonRequest buttonRequest) {
		String widgetUrl = buildWidgetUrl(buttonRequest);
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
	 * @param buttonRequest
	 *            the data to sent to the server
	 * @return the HTML tag string
	 */
	public String buildIframeWidget(ButtonRequest request) {

		String widgetUrl = buildWidgetUrl(request);
		StringBuffer res = new StringBuffer();
		res.append("<iframe id='tipButtonFrame' scrolling='no' frameborder='0' style='border:none; overflow:hidden; height:22px;' allowTransparency='true' src='");

		res.append(widgetUrl);
		res.append("'></iframe>");
		return res.toString();
	}

}
