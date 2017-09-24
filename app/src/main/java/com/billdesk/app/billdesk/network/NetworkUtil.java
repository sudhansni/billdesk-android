package com.billdesk.app.billdesk.network;

import android.util.Base64;

import com.billdesk.app.billdesk.models.BillDeskRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by sudha on 24-09-2017.
 */

public class NetworkUtil {

    private static final String BASIC_AUTH_USERNAME = "admin";
    private static final String BASIC_AUTH_PASSWORD = "1234";
    public static final String BASE_URL = "http://www.billdesk.com.au/api/v1/";
    public static final String REGISTRATION_URL = BASE_URL + "sendCode";
    public static final String VERIFY_OTP_URL = BASE_URL + "validateCode";
    private static final Gson gson = new Gson();
    private static HashMap<String, String> headers = null;

    private NetworkUtil () {

    }

    public static Map<String, String> addDefaultHeaders() {
        if (headers == null) {
            headers = new HashMap<>();
            String credentials = BASIC_AUTH_USERNAME + ":" + BASIC_AUTH_PASSWORD;
            headers.put("Authorization", "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP));
            headers.put("source-system","android");
            headers.put("deviceId", UUID.randomUUID().toString());
        }
        return headers;
    }

    public static Map<String, String> getParams(BillDeskRequest request) {
        if (request != null) {
            return gson.fromJson(gson.toJson(request), new TypeToken<HashMap<String, String>>() {
            }.getType());
        }
        return null;
    }


}
