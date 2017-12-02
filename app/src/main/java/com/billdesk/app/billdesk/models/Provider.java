package com.billdesk.app.billdesk.models;

import com.google.gson.annotations.SerializedName;

public class Provider {

    @SerializedName("iProviderId")
    private String iProviderId;

    @SerializedName("vcProviderName")
    private String vcProviderName;

    @SerializedName("vcImageUrl")
    private String vcImageUrl;

    @SerializedName("vcWebUrl")
    private String vcWebUrl;

    public Provider() {

    }

    public Provider(String name) {
        vcProviderName = name;
    }

    public String getiProviderId() {
        return iProviderId;
    }

    public void setiProviderId(String iProviderId) {
        this.iProviderId = iProviderId;
    }

    public String getVcProviderName() {
        return vcProviderName;
    }

    public void setVcProviderName(String vcProviderName) {
        this.vcProviderName = vcProviderName;
    }

    public String getVcImageUrl() {
        return vcImageUrl;
    }

    public void setVcImageUrl(String vcImageUrl) {
        this.vcImageUrl = vcImageUrl;
    }

    public String getVcWebUrl() {
        return vcWebUrl;
    }

    public void setVcWebUrl(String vcWebUrl) {
        this.vcWebUrl = vcWebUrl;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "iProviderId='" + iProviderId + '\'' +
                ", vcProviderName='" + vcProviderName + '\'' +
                ", vcImageUrl='" + vcImageUrl + '\'' +
                ", vcWebUrl='" + vcWebUrl + '\'' +
                '}';
    }
}
