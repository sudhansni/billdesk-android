package com.billdesk.app.billdesk.models;

import com.google.gson.annotations.SerializedName;

public class RegisterMobileRequest implements BillDeskRequest{

    @SerializedName("mobileNumber")
    private String mobileNumber;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "RegisterMobileRequest{" +
                "mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
