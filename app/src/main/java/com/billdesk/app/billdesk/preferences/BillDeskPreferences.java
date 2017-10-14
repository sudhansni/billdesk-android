package com.billdesk.app.billdesk.preferences;

import android.content.Context;
import android.content.SharedPreferences;


public class BillDeskPreferences {
    private static final String PREFERENCE_NAME = "BILLDESK";
    private static final String KEY_USER_ID = "KEY_USER_ID";
    private static final String KEY_IS_USER_VALIDATED = "KEY_IS_VALIDATED";
    private static final String KEY_IS_PROFILE_SEEN = "KEY_IS_PROFILE_SEEN";
    private static final String KEY_HAS_MULTIPLE_USERS = "KEY_HAS_MULTIPLE_USERS";
    private static final String KEY_MOBILE_NUMBER = "KEY_MOBILE";
    private static final String KEY_USER_FULLNAME = "KEY_USER_FULLNAME";
    private static final String KEY_USER_EMAIL = "KEY_USER_EMAIL";
    private static SharedPreferences preferences;
    private static String CURRENT_MOBILE_NUMBER;

    public static void initialize(Context applicationContext) {
        preferences = applicationContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static void setUserId(long userId) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(CURRENT_MOBILE_NUMBER + KEY_USER_ID, userId);
        editor.apply();
    }

    public static long getUserId() {
       return preferences.getLong(CURRENT_MOBILE_NUMBER + KEY_USER_ID, -1);
    }

    public static String getMobileNumber() {
        return preferences.getString(KEY_MOBILE_NUMBER, "");
    }

    public static boolean isUserValidated() {
        return preferences.getBoolean(CURRENT_MOBILE_NUMBER + KEY_IS_USER_VALIDATED, false);
    }

    public static boolean hasUserSeenProfileScreen() {
        return preferences.getBoolean(CURRENT_MOBILE_NUMBER + KEY_IS_PROFILE_SEEN, false);
    }

    public static String getUserEmail() {
        return preferences.getString(CURRENT_MOBILE_NUMBER + KEY_USER_EMAIL, "");
    }

    public static String getUserFullName() {
        return preferences.getString(CURRENT_MOBILE_NUMBER + KEY_USER_FULLNAME, "");
    }

    public static void setMobileNumber(String mobileNumber) {
        CURRENT_MOBILE_NUMBER = mobileNumber;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_MOBILE_NUMBER, mobileNumber);
        editor.apply();
    }

    public static void setUserValidated() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(CURRENT_MOBILE_NUMBER + KEY_IS_USER_VALIDATED, true);
        editor.apply();
    }

    public static void setProfileSeen (boolean hasSeenProfile) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(CURRENT_MOBILE_NUMBER + KEY_IS_PROFILE_SEEN, hasSeenProfile);
        editor.apply();
    }

    public static void setFullName(String fullName) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CURRENT_MOBILE_NUMBER + KEY_USER_FULLNAME, fullName);
        editor.apply();
    }

    public static void setEmail(String email) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CURRENT_MOBILE_NUMBER + KEY_USER_EMAIL, email);
        editor.apply();
    }
}
