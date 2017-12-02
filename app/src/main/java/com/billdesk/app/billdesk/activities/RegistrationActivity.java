package com.billdesk.app.billdesk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.models.RegisterMobileRequest;
import com.billdesk.app.billdesk.models.RegisterMobileResponse;
import com.billdesk.app.billdesk.network.NetworkManager;
import com.billdesk.app.billdesk.preferences.BillDeskPreferences;
import com.billdesk.app.billdesk.utils.UiUtils;
import com.billdesk.app.billdesk.views.CustomEditText;


public class RegistrationActivity extends BaseActivity {


    private CustomEditText mobileNumberEditText;
    private static final int MAX_LENGTH_MOBILE_NUMBER = 9;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BillDeskPreferences.isUserValidated()) {
            if (BillDeskPreferences.hasUserSeenProfileScreen()) {
                // launchCardsActivity();
            } else {
                //launchProfileActivity();
            }
           // finish();
        }
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView = findViewById(R.id.terms_and_conditions_text);

        Spannable word = new SpannableString("By signing up,\n you agree to the ");
        word.setSpan(new ForegroundColorSpan(getResources().getColor(android.R.color.black)), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(word);

        Spannable wordTwo = new SpannableString("Terms of Service");
        wordTwo.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(wordTwo);

        mobileNumberEditText = findViewById(R.id.numberInput);
        mobileNumberEditText.setMaxLength(MAX_LENGTH_MOBILE_NUMBER);
    }

    public void onNextButtonClicked(View view) {
        BillDeskPreferences.setMobileNumber(UiUtils.AUSTRALIA_COUNTRY_CODE + mobileNumberEditText.getText().toString());
        makeVolleyCall();

    }

    private boolean validateInputs() {
        boolean result = true;
        final String mobile = mobileNumberEditText.getText().toString();
        if (mobile.isEmpty()) {
            mobileNumberEditText.requestFocus();
            mobileNumberEditText.setError(mobileNumberEditText.getContext().getString(R.string.error_desc_mobile_required));
            return false;
        }

        if (mobile.length() != MAX_LENGTH_MOBILE_NUMBER) {
            result = false;
        }
        mobileNumberEditText.requestFocus();
        mobileNumberEditText.setError(result ? null : mobileNumberEditText.getContext().getString(R.string.error_desc_invalid_mobile));
        return result;
    }

    private void makeVolleyCall () {
        UiUtils.hideSoftKeyboard(mobileNumberEditText);
        if (!(validateInputs() && isNetworkConnected())) {
            return;
        }

        showProgress();

        RegisterMobileRequest registerMobileRequest = new RegisterMobileRequest();
        registerMobileRequest.setMobileNumber(UiUtils.AUSTRALIA_COUNTRY_CODE + mobileNumberEditText.getText().toString());

        Response.Listener<RegisterMobileResponse> responseListener = new Response.Listener<RegisterMobileResponse>() {
            @Override
            public void onResponse(RegisterMobileResponse response) {
                hideProgress();
                if (response == null) {
                    showGenericError();
                } else if (response.isSuccess()) {
                    // Save user id to preference
                    BillDeskPreferences.setUserId(response.getUserId());

                    // Launch next activity
                    launchOtpVerifyActivity();
                } else {
                    showGenericError();
                }
            }
        };

        NetworkManager.registerMobile(registerMobileRequest, responseListener, errorListener);
    }

    private void launchOtpVerifyActivity() {
        final Intent startOtpIntent = new Intent(this, OTPActivity.class);
        startActivity(startOtpIntent);
    }

    private void launchCardsActivity() {
        final Intent cardsIntent = new Intent(this, ViewCardsActivity.class);
        startActivity(cardsIntent);
    }

    private void launchProfileActivity() {
        final Intent profileActivityIntent = new Intent(this, ProfileDetailsActivity.class);
        profileActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(profileActivityIntent);
    }
}
