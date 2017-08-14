package com.billdesk.app.billdesk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.views.CustomEditText;

/**
 * Created by jprince on 7/8/2017.
 */

public class RegistrationActivity extends AppCompatActivity {


    private CustomEditText mobileNumberEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView = (TextView) findViewById(R.id.terms_and_conditions_text);

        Spannable word = new SpannableString("By signing up,\n you agree to the ");
        word.setSpan(new ForegroundColorSpan(getResources().getColor(android.R.color.black)), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(word);

        Spannable wordTwo = new SpannableString("Terms of Service");
        wordTwo.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.titleText)), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(wordTwo);

        ImageButton nextButton = (ImageButton)findViewById(R.id.arrow_right);

        mobileNumberEditText = (CustomEditText) findViewById(R.id.numberInput);

        final Intent startOTPActivity = new Intent(this, OTPActivity.class);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder builder = new StringBuilder("+61");
                builder.append(mobileNumberEditText.getText());
                startOTPActivity.putExtra(OTPActivity.NUMBER_EXTRA,  builder.toString());
                startActivity(startOTPActivity);
            }
        });
    }
}
