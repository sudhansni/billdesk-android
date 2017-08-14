package com.billdesk.app.billdesk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.views.CustomEditText;

/**
 * Created by rajesh on 7/13/2017.
 */

public class ProfileDetailsActivity extends AppCompatActivity{

    private CustomEditText mobileNumberEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String number = getIntent().getStringExtra(OTPActivity.NUMBER_EXTRA);

        TextView textView = (TextView) findViewById(R.id.numberView);

        textView.setText(number);

        Button createAccountButton = (Button) findViewById(R.id.btutton_create_account);
        final Intent startCategoryActivity = new Intent(this, CardsActivity.class);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(startCategoryActivity);
            }
        });


    }
}
