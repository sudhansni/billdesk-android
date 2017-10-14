package com.billdesk.app.billdesk.models;

import com.google.gson.annotations.SerializedName;

public class UserProfileResponse extends BaseResponse {

    @SerializedName("personalDetails")
    private PersonalDetails personalDetails;

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    @Override
    public String toString() {
        return "UserProfileResponse{" +
                "personalDetails=" + personalDetails +
                "} " + super.toString();
    }
}
