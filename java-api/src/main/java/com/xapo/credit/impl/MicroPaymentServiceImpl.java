package com.xapo.credit.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.bouncycastle.asn1.microsoft.MicrosoftObjectIdentifiers;

import com.google.gson.Gson;
import com.xapo.credit.MicroPaymentService;
import com.xapo.exception.XapoException;
import com.xapo.model.PaymentRequest;
import com.xapo.model.XapoResponse;
import com.xapo.utils.encrypt.MCrypt;
import com.xapo.utils.json.JsonMarshaller;

public class MicroPaymentServiceImpl implements MicroPaymentService {
	 private static final String BASE_URL = "https://api.xapo.com/v1/credit/";
//	private static final String BASE_URL = "http://dev.xapo.com/api/v1/credit/";

	MCrypt mCrypt;

	JsonMarshaller jsonMarsheler;

	String secretKey;

	String appId;

	public MicroPaymentServiceImpl(MCrypt mCrypt, JsonMarshaller jsonMarsheler,
			String secretKey, String appId) {
		this.mCrypt = mCrypt;
		this.jsonMarsheler = jsonMarsheler;
		this.secretKey = secretKey;
		this.appId = appId;

	}

	@Override
	public XapoResponse pay(PaymentRequest pr) throws XapoException, ClientProtocolException, IOException {
		String jsonToEncript=jsonMarsheler.paymentToJson(pr);
		
		System.out.println(jsonToEncript);
		XapoResponse xr;
		
		try{
		
			String encriptedPayload=mCrypt.encrypt(secretKey, jsonToEncript);
			Map<String, String> params = new HashMap<String, String>();
			params.put("appID", appId);
			params.put("hash",encriptedPayload );
			xr = new Gson().fromJson(makePostRequest(urlEncodeParams(params)), XapoResponse.class); 
			
			if(!xr.getCode().equals("Success")){
				
				throw new XapoException(xr.getCode()+":"+xr.getMessage());
				
			}
		
		}catch(Exception e){
			e.printStackTrace();
			
			throw new XapoException(e);
		}
		
		

			
		
		
		return xr;
				
		
//		
	//	makePostRequest(urlEncodeParams(params));
		//AES.decrypt(encriptedPayload);
		
		
		//makeRequestWithHttpClient(appId, encriptedPayload);
		
	}

	private String makeRequestWithHttpClient(String appId, String hash)
			throws ClientProtocolException, IOException {
		String url = BASE_URL;

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("appID", appId));
		urlParameters.add(new BasicNameValuePair("hash", hash));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		System.out.println(post.toString() + " entities "
				+ post.getEntity().toString());

		HttpResponse response = client.execute(post);
		System.out.println("Response Code : "
				+ response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuilder result = new StringBuilder();
		String line = "";
		while ((line = rd.readLine()) != null) {

			result.append(line);

		}

		System.out.println(result);

		return result.toString();

		// System.out.println("The result is "+result);
	}

	@Deprecated
	private String makePostRequest(String encodedParams) throws Exception {
		System.out.println("Sending enconded params " + encodedParams);

		URL url = new URL(BASE_URL);

		HttpURLConnection conn = null;

		conn = (HttpURLConnection) url.openConnection();
		byte[] postBytes = encodedParams.getBytes();
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length",
				String.valueOf(postBytes.length));
		conn.getOutputStream().write(postBytes);
		conn.getOutputStream().close();

		if (conn.getResponseCode() != 200) {

			throw new Exception("The connection failed");

		} 
			String responseStr = inputStreamToString(conn.getInputStream());
			
			return responseStr;

	}

	@Deprecated
	private String urlEncodeParams(Map<String, String> params) {
		String result = "";

		if (params != null && params.size() > 0) {
			try {
				StringBuilder data = new StringBuilder();
				for (Entry<String, String> kvp : params.entrySet()) {
					if (data.length() > 0)
						data.append('&');

					data.append(URLEncoder.encode(kvp.getKey(), "UTF-8"));
					data.append('=');
					data.append(URLEncoder.encode(kvp.getValue(), "UTF-8"));
				}
				result = data.toString();
			} catch (UnsupportedEncodingException e) {
			}
		}

		return result;
	}

	private String inputStreamToString(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		StringBuilder responseStringBuilder = new StringBuilder();
		String line = "";

		while ((line = reader.readLine()) != null)
			responseStringBuilder.append(line);

		return responseStringBuilder.toString();
	}

}
