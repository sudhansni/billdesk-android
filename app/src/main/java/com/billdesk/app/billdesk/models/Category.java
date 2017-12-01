package com.billdesk.app.billdesk.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

   @SerializedName("categoryName")
    private String categoryName;

    @SerializedName("categoryId")
    private int categoryId;

    @SerializedName("providers")
    private List<Provider> providers;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                ", categoryId=" + categoryId +
                ", providers=" + providers +
                '}';
    }
}
