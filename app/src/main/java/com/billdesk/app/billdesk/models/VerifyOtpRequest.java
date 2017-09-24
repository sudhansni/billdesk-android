package com.billdesk.app.billdesk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sudha on 24-09-2017.
 */

public class VerifyOtpRequest implements BillDeskRequest {

    @SerializedName("userId")
    private long userId;

    @SerializedName("code")
    private int otpCode;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(int otpCode) {
        this.otpCode = otpCode;
    }

    @Override
    public String toString() {
        return "VerifyOtpRequest{" +
                "userId=" + userId +
                ", otpCode=" + otpCode +
                '}';
    }
}
