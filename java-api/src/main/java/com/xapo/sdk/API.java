package com.xapo.sdk;

import java.io.IOException;
import java.util.UUID;

import mjson.Json;

import org.apache.commons.io.IOUtils;

import retrofit.RestAdapter;
import retrofit.client.Response;

import com.xapo.model.XapoResponse;
import com.xapo.utils.encrypt.MCrypt;
import com.xapo.utils.encrypt.ZeroPadding;

/**
 * Xapo's API.
 *
 * This class allows the interaction with bitcoins APIs provided with Xapo.
 *
 * Created by frepond on 6/11/14.
 */
public class API {
    /**
     * The id of the TPA doing the credit.
     */
    private String appId;

    /**
     * The TPA secret used to encrypt payload.
     */
    private String secret;

    /**
     * The endpoint URL that returns the payment widget.
     */
    private XapoService service;

    public API(String url, String appId, String secret) {
        this.appId = appId;
        this.secret = secret;

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(url)
                .build();
        this.service = restAdapter.create(XapoService.class);
    }

    /**
     * Transfer a given amount from the main wallet of the TPA to a given sub account.
     *
     * @param to the destination of the credit.
     * @param amount the amount to be credited.
     * @param currency the currency of the operation (SAT|BTC).
     */
    public Json credit(String to, float amount, String currency,
                          String comments, String subject) throws RuntimeException {
        String id = UUID.randomUUID().toString();

        return this.credit(to, amount, id, currency, comments, subject);
    }

    /**
     * Transfer a given amount from the main wallet of the TPA to a given sub account.
     *
     * @param to the destination of the credit.
     * @param amount the amount to be credited.
     * @param requestId a unique identifier for the credit operation.
     * @param currency the currency of the operation (SAT|BTC).
     */
    public Json credit(String to, float amount, String requestId, String currency,
                          String comments, String subject) throws RuntimeException {
        Response response = null;
        String jsonString = null;
        MCrypt mcrypt = new MCrypt(new ZeroPadding());
        long timestamp = System.currentTimeMillis();
        Json payload = Json.object()
                .set("to", to)
                .set("amount", amount)
                .set("currency", currency)
                .set("comments", comments)
                .set("subject", subject)
                .set("timestamp", timestamp)
                .set("unique_request_id", requestId);

        String jsonPayload = payload.toString();

        try {
            String encryptedPayload = mcrypt.encrypt(this.secret, jsonPayload);
            response = service.credit(this.appId, encryptedPayload);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        
		

        try {
            jsonString = IOUtils.toString(response.getBody().in(), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("Error reading credit response.");
        }

        return Json.read(jsonString);
    }
    
   
}
