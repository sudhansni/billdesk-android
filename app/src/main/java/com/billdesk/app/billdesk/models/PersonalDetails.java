package com.billdesk.app.billdesk.models;

import com.google.gson.annotations.SerializedName;

public class PersonalDetails {

    @SerializedName("userId")
    private long userId;

    @SerializedName("mobileNumber")
    private String mobileNumber;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("emailAddress")
    private String emailAddress;

    @SerializedName("profileImage")
    private String profileImage;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return "PersonalDetails{" +
                "userId=" + userId +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }
}
