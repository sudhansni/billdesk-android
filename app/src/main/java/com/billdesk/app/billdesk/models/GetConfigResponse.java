package com.billdesk.app.billdesk.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetConfigResponse extends BaseResponse{

    @SerializedName("categories")
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "GetConfigResponse{" +
                "categories=" + categories +
                "} " + super.toString();
    }
}
