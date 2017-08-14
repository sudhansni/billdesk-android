package com.billdesk.app.billdesk.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rajesh on 7/23/2017.
 */

public class Provider {
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
}
