package com.billdesk.app.billdesk.network;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

import okio.ByteString;

/**
 * Created by sudha on 24-09-2017.
 */

public class NetworkUtil {
    public static String basic(String username, String password) {
        String credentials = username + ":" + password;
        return Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
    }
}
