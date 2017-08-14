package com.billdesk.app.billdesk.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rajesh on 7/27/2017.
 */

public class GetConfigResponse {
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("success")
    private boolean success;
    @SerializedName("categories")
    private List<Category> categories;

    public String getTime() {
        return time;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
