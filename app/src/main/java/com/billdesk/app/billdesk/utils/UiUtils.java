package com.billdesk.app.billdesk.utils;

import java.util.Random;

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
}
