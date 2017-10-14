package com.billdesk.app.billdesk.models;

import com.google.gson.annotations.SerializedName;

public class RegisterMobileResponse extends BaseResponse {

    @SerializedName("userId")
    private long userId;

    @SerializedName("message")
    private String message;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RegisterMobileResponse{" +
                "userId=" + userId +
                ", message='" + message + '\'' +
                "} " + super.toString();
    }
}
