package com.billdesk.app.billdesk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sudha on 02-10-2017.
 */

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
