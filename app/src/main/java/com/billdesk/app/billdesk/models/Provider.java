package com.billdesk.app.billdesk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Provider implements Parcelable {
    @SerializedName("iProviderId")
    @Expose
    private String iProviderId;
    @SerializedName("vcProviderName")
    @Expose
    private String vcProviderName;
    @SerializedName("vcImageUrl")
    @Expose
    private String vcImageUrl;
    @SerializedName("vcWebUrl")
    @Expose
    private String vcWebUrl;

    public Provider(){

    }

    protected Provider(Parcel in) {
        iProviderId = in.readString();
        vcProviderName = in.readString();
        vcImageUrl = in.readString();
        vcWebUrl = in.readString();
    }

    public static final Creator<Provider> CREATOR = new Creator<Provider>() {
        @Override
        public Provider createFromParcel(Parcel in) {
            return new Provider(in);
        }

        @Override
        public Provider[] newArray(int size) {
            return new Provider[size];
        }
    };

    public String getiProviderId() {
        return iProviderId;
    }

    public String getVcProviderName() {
        return vcProviderName;
    }

    public String getVcImageUrl() {
        return vcImageUrl;
    }

    public String getVcWebUrl() {
        return vcWebUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iProviderId);
        dest.writeString(vcProviderName);
        dest.writeString(vcImageUrl);
        dest.writeString(vcWebUrl);
    }
}
