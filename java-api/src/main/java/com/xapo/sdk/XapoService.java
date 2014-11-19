package com.xapo.sdk;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

import java.util.List;

import com.xapo.model.XapoResponse;

/**
 * XAPO API endpoints.
 *
 * Created by frepond on 6/11/14.
 */
public interface XapoService {
    @GET("/credit/")
    XapoResponse credit(@Query("appID") String appID, @Query("hash") String hash);
}
