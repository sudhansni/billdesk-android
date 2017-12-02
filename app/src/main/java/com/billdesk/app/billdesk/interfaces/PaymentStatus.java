package com.billdesk.app.billdesk.interfaces;

import android.support.annotation.ColorRes;

public enum PaymentStatus {
    PAID (android.R.color.holo_green_dark),
    PENDING (android.R.color.holo_blue_dark),
    OVERDUE (android.R.color.holo_red_dark);

    private int color;

    PaymentStatus(@ColorRes int colorId) {
        this.color = colorId;
    }

    public int getColor() {
        return color;
    }
}
