package com.billdesk.app.billdesk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.models.SendCodeRequest;
import com.billdesk.app.billdesk.models.SendCodeResponse;
import com.billdesk.app.billdesk.network.GsonPostRequest;
import com.billdesk.app.billdesk.network.NetworkManager;
import com.billdesk.app.billdesk.services.ServiceUtil;
import com.billdesk.app.billdesk.views.CustomEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jprince on 7/8/2017.
 */

public class RegistrationActivity extends AppCompatActivity {


    private CustomEditText mobileNumberEditText;
    private static final int MAX_LENGTH_MOBILE_NUMBER = 9;
    private Call<SendCodeResponse> sendCodeResponseCall;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
       makeVolleyCall();
    }

    private void makeVolleyCall () {
        com.android.volley.Response.Listener<SendCodeResponse> responseListener = new com.android.volley.Response.Listener<SendCodeResponse>() {
            @Override
            public void onResponse(SendCodeResponse response) {
               System.out.println(response.toString());
            }
        };

        com.android.volley.Response.ErrorListener errorListener = new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               error.printStackTrace();
            }
        };
        GsonPostRequest<SendCodeResponse> request = new GsonPostRequest<>("http://www.billdesk.com.au/api/v1/sendCode",
                SendCodeResponse.class, null, responseListener, errorListener);
        NetworkManager.addToRequestQueue(request);
    }
    private void makeServiceCall () {
        SendCodeRequest sendCodeRequest = new SendCodeRequest();
        sendCodeRequest.setMobileNumber(mobileNumberEditText.getText().toString());
        sendCodeResponseCall = ServiceUtil.getService().sendCode(mobileNumberEditText.getText().toString());


        sendCodeResponseCall.enqueue(new Callback<SendCodeResponse>() {
            @Override
            public void onResponse(Call<SendCodeResponse> call, Response<SendCodeResponse> response) {
                if (sendCodeResponseCall == null) {
                    return;
                }
                SendCodeResponse sendCodeResponse = response.body();
                if (sendCodeResponse == null) {
                    // TODO:: Show Generic Error
                    Log.d("Send Code Response :: ", "is null");
                    return;
                }
                Log.d("SendCodeResponse :: ", sendCodeResponse.toString());
                if (sendCodeResponse.isSuccess()) {
                    // TODO:: Save UserId to Preference
                    launchOtpVerifyActivity();
                } else {
                    // TODO:: Handle Error

                }
            }

            @Override
            public void onFailure(Call<SendCodeResponse> call, Throwable t) {
                // TODO :: Hide Progress & Show Generic Error
                Log.e(RegistrationActivity.class.getName(), "Exception while registering mobile number", t);
            }
        });

    }

    private void launchOtpVerifyActivity() {
        final Intent startOtpIntent = new Intent(this, OTPActivity.class);
        startOtpIntent.putExtra(OTPActivity.NUMBER_EXTRA,  "+61" + mobileNumberEditText.getText().toString());
        startActivity(startOtpIntent);
    }
}
