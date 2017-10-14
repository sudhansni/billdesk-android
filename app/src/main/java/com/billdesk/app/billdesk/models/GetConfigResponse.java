package com.billdesk.app.billdesk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetConfigResponse implements Parcelable{
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("success")
    private boolean success;
    @SerializedName("categories")
    private ArrayList<Category> categories;

    protected GetConfigResponse(Parcel in) {
        time = in.readString();
        success = in.readByte() != 0;
        categories = in.createTypedArrayList(Category.CREATOR);
    }

    public static final Creator<GetConfigResponse> CREATOR = new Creator<GetConfigResponse>() {
        @Override
        public GetConfigResponse createFromParcel(Parcel in) {
            return new GetConfigResponse(in);
        }

        @Override
        public GetConfigResponse[] newArray(int size) {
            return new GetConfigResponse[size];
        }
    };

    public String getTime() {
        return time;
    }

    public boolean isSuccess() {
        return success;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(time);
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeTypedList(categories);
    }
}
