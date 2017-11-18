package com.billdesk.app.billdesk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.models.BaseResponse;
import com.billdesk.app.billdesk.models.VerifyOtpRequest;
import com.billdesk.app.billdesk.network.NetworkManager;
import com.billdesk.app.billdesk.preferences.BillDeskPreferences;
import com.billdesk.app.billdesk.utils.UiUtils;
import com.goodiebag.pinview.Pinview;


public class OTPActivity extends BaseActivity {

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
        String formattedPhoneNumber = UiUtils.getFormattedPhoneNumber(BillDeskPreferences.getMobileNumber());
        phoneNumberView.setText(formattedPhoneNumber);
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
                if (response == null) {
                    showGenericError();
                }
                else if (response.isSuccess()) {

                    // Save to preference
                    BillDeskPreferences.setUserValidated();

                    // Launch next activity
                    launchProfileActivity();
                } else {
                    // Handle error
                    Log.d("OTPActivity", "Response :: " + response);
                    launchProfileActivity(); //TODO:: Remove this after testing and show error message
                }
            }
        };

        NetworkManager.verifyOtpCode(otpRequest, responseListener, errorListener);
    }



    private void launchProfileActivity() {
        final Intent profileActivityIntent = new Intent(this, ProfileDetailsActivity.class);
        profileActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(profileActivityIntent);
    }


}
