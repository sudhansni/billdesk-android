package com.billdesk.app.billdesk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.billdesk.app.billdesk.R;
import com.goodiebag.pinview.Pinview;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

/**
 * Created by jprince on 7/9/2017.
 */

public class OTPActivity extends AppCompatActivity {

    public static String NUMBER_EXTRA = "NUMBER_EXTRA";
    private ImageButton rightImageButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_otp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView phoneNumberView = (TextView) findViewById(R.id.phone_number_text);
        rightImageButton = (ImageButton) findViewById(R.id.arrow_right);
        Pinview pinview = (Pinview) findViewById(R.id.piview_otp);
        pinview.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean b) {
                rightImageButton.setVisibility(View.VISIBLE);
            }
        });

        String formattedPhoneNumber = getFormattedPhoneNumber(getIntent().getStringExtra(NUMBER_EXTRA));
        phoneNumberView.setText(formattedPhoneNumber);

        final Intent startProfileActivity = new Intent(this, ProfileDetailsActivity.class);
        startProfileActivity.putExtra(OTPActivity.NUMBER_EXTRA, formattedPhoneNumber);
        rightImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(startProfileActivity);
            }
        });

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


}
