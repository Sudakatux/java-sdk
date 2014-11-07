package com.xapo.sdk;

import com.xapo.utils.encrypt.MCrypt;
import com.xapo.utils.encrypt.ZeroPadding;
import mjson.Json;
import retrofit.RestAdapter;
import retrofit.client.Response;

import java.util.UUID;

/**
 * Created by frepond on 6/11/14.
 */
public class API {
    private String appId;
    private String secret;
    private XapoService service;

    public API(String url, String appId, String secret) {
        this.appId = appId;
        this.secret = secret;

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(url)
                .build();
        this.service = restAdapter.create(XapoService.class);
    }

    public Json credit(String to, float amount, String currency,
                          String comments, String subject) throws RuntimeException {
        String id = UUID.randomUUID().toString();

        return this.credit(to, amount, id, currency, comments, subject);
    }


    public Json credit(String to, float amount, String requestId, String currency,
                          String comments, String subject) throws RuntimeException {
        Response response = null;
        MCrypt mcrypt = new MCrypt(new ZeroPadding());
        long timestamp = System.currentTimeMillis();
        Json payload = Json.object()
                .set("to", to)
                .set("currency", amount)
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

        return Json.read(response.getBody().toString());
    }
}
