package com.billdesk.app.billdesk.services;

import com.billdesk.app.billdesk.models.GetConfigResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rajesh on 7/23/2017.
 */

public interface BillDeskClient {

    @GET("v1/getConfig")
    Call<GetConfigResponse> getCategories();
}
