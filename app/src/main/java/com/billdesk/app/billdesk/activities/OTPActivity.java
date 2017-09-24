package com.billdesk.app.billdesk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.models.BaseResponse;
import com.billdesk.app.billdesk.models.VerifyOtpRequest;
import com.billdesk.app.billdesk.network.NetworkManager;
import com.billdesk.app.billdesk.preferences.BillDeskPreferences;
import com.goodiebag.pinview.Pinview;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

/**
 * Created by jprince on 7/9/2017.
 */

public class OTPActivity extends BaseActivity {

    public static String NUMBER_EXTRA = "NUMBER_EXTRA";
    private ImageButton rightImageButton;
    private Pinview pinView;
    private TextView phoneNumberView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_otp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        phoneNumberView = findViewById(R.id.phone_number_text);
        rightImageButton = findViewById(R.id.arrow_right);
        pinView = findViewById(R.id.piview_otp);
        pinView.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean b) {
                rightImageButton.setVisibility(View.VISIBLE);
            }
        });


        rightImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeVolleyCall();
            }
        });

    }

    private void makeVolleyCall() {
        VerifyOtpRequest otpRequest = new VerifyOtpRequest();
        otpRequest.setOtpCode(Integer.valueOf(pinView.getValue()));
        otpRequest.setUserId(BillDeskPreferences.getUserId());

        Response.Listener<BaseResponse> responseListener = new Response.Listener<BaseResponse>() {
            @Override
            public void onResponse(BaseResponse response) {
                if (response != null && response.isSuccess()) {

                    // Save to preference

                    // Launch next activity
                    launchProfileActivity();
                } else {
                    // Handle error
                }
            }
        };

        NetworkManager.verifyOtpCode(otpRequest, responseListener, errorListener);
    }

    private String getFormattedPhoneNumber(String phoneNumber){

        try {

            PhoneNumberUtil pnu = PhoneNumberUtil.getInstance();
            Phonenumber.PhoneNumber pn = pnu.parse(phoneNumber, "AU");
            return pnu.format(pn, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);

        } catch (NumberParseException e) {

            return "";

        }
    }

    private void launchProfileActivity() {
        String formattedPhoneNumber = getFormattedPhoneNumber(getIntent().getStringExtra(NUMBER_EXTRA));
        phoneNumberView.setText(formattedPhoneNumber);

        final Intent startProfileActivity = new Intent(this, ProfileDetailsActivity.class);
        startProfileActivity.putExtra(OTPActivity.NUMBER_EXTRA, formattedPhoneNumber);
    }


}
