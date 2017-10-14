package com.billdesk.app.billdesk.interfaces;

import android.support.v7.widget.AppCompatEditText;
import android.view.View;

public class EditTextFocusChangeListener implements View.OnFocusChangeListener {
    @Override
    public void onFocusChange(final View view, final boolean b) {
        if (view instanceof AppCompatEditText) {
            AppCompatEditText editText = (AppCompatEditText) view;
            if (editText.getError() != null) {
                editText.setError(null);
            }
        }
    }
}
