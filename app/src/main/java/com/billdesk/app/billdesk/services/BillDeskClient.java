package com.billdesk.app.billdesk.services;

import com.billdesk.app.billdesk.models.GetConfigResponse;
import com.billdesk.app.billdesk.models.RegisterMobileResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by rajesh on 7/23/2017.
 */

public interface BillDeskClient {

    // Send Code
    @POST("v1/sendCode")
    Call<RegisterMobileResponse> sendCode(@Body String mobileNumber);

    @GET("v1/getConfig")
    Call<GetConfigResponse> getCategories();
}
