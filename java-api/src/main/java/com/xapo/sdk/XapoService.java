package com.xapo.sdk;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

import java.util.List;

/**
 * XAPO API endpoints.
 *
 * Created by frepond on 6/11/14.
 */
public interface XapoService {
    @GET("/credit/")
    Response credit(@Query("appID") String appID, @Query("hash") String hash);
}
