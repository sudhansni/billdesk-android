package com.billdesk.app.billdesk.models;

import com.google.gson.annotations.SerializedName;

public class UserProfileRequest implements BillDeskRequest {
    @SerializedName("userId")
    private long userId;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("emailAddress")
    private String emailAddress;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "UserProfileRequest{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
