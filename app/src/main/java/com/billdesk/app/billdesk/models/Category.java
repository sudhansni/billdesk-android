package com.billdesk.app.billdesk.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rajesh on 7/19/2017.
 */

public class Category {
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("categoryId")
    @Expose
    private int categoryId;
    @SerializedName("providers")
    @Expose
    private List<Provider> providers;

    public String getCategoryName() {
        return categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public List<Provider> getProviders() {
        return providers;
    }
}
