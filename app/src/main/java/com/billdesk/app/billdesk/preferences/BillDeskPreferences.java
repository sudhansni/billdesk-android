package com.billdesk.app.billdesk.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sudha on 24-09-2017.
 */

public class BillDeskPreferences {
    private static final String PREFERENCE_NAME = "BILLDESK";
    private static final String KEY_USER_ID = "KEY_USER_ID";
    private static final String KEY_IS_USER_VALIDATED = "KEY_IS_VALIDATED";
    private static final String KEY_MOBILE_NUMBER = "KEY_MOBILE";
    private static SharedPreferences preferences;

    public static void initialize(Context applicationContext) {
        preferences = applicationContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static void setUserId(long userId) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(KEY_USER_ID, userId);
        editor.apply();
    }

    public static long getUserId() {
       return preferences.getLong(KEY_USER_ID, -1);
    }

    public static void setMobileNumber(String mobileNumber) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_MOBILE_NUMBER, mobileNumber);
        editor.apply();
    }
}
