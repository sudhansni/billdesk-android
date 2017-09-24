package com.billdesk.app.billdesk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Random;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by rajesh on 7/27/2017.
 */

public class UiUtils {
    private static int accentColors[] = {
            android.R.color.holo_blue_light,
            android.R.color.holo_green_dark,
            android.R.color.holo_green_light,
            android.R.color.holo_blue_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_orange_light,
            android.R.color.holo_purple,
            android.R.color.holo_red_dark,
            android.R.color.holo_red_light

    };

    public static int getRandomAccentColor(){
        return accentColors[new Random().nextInt(accentColors.length)];
    }

    public static void hideSoftKeyboard(final View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static boolean isNetworkConnected(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();

    }

}
