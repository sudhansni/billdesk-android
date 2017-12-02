package com.billdesk.app.billdesk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.models.UserProfileRequest;
import com.billdesk.app.billdesk.models.UserProfileResponse;
import com.billdesk.app.billdesk.network.NetworkManager;
import com.billdesk.app.billdesk.preferences.BillDeskPreferences;
import com.billdesk.app.billdesk.views.CustomEditText;

public class ProfileDetailsActivity extends BaseActivity {

    private CustomEditText mobileNumberEditText;
    private CustomEditText fullNameEditText;
    private CustomEditText emailEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView = findViewById(R.id.numberView);
        textView.setText(BillDeskPreferences.getMobileNumber());

        fullNameEditText = findViewById((R.id.nameInput));
        fullNameEditText.setText(BillDeskPreferences.getUserFullName());

        emailEditText = findViewById((R.id.emailInput));
        emailEditText.setText(BillDeskPreferences.getUserEmail());

        Button createAccountButton = findViewById(R.id.button_create_account);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO:: If no change in data, skip network call
                // TODO:: If data has changed, Validate inputs
                updateUserProfile();
            }
        });

        // Initialize User Details from Preference
        // Make network call to get user details
    }

    private void getUserProfile() {

    }

    private void updateUserProfile () {
        UserProfileRequest userProfileRequest = new UserProfileRequest();
        userProfileRequest.setUserId(BillDeskPreferences.getUserId());
        userProfileRequest.setEmailAddress(emailEditText.getText().toString());
        userProfileRequest.setFullName(fullNameEditText.getText().toString());

        Response.Listener<UserProfileResponse> responseListener = new Response.Listener<UserProfileResponse>() {
            @Override
            public void onResponse(UserProfileResponse response) {
                if (response == null) {
                    showGenericError();
                }
                 else if (response.isSuccess()) {

                    // Save email & full name to preference
                    BillDeskPreferences.setEmail(response.getPersonalDetails().getEmailAddress());
                    BillDeskPreferences.setFullName(response.getPersonalDetails().getFullName());

                    // Launch next activity
                    launchCategoryActivity();
                } else {
                    // Handle error
                    Log.d("ProfileDetails", "Response :: " + response);
                    showGenericError();
                }
            }
        };

        NetworkManager.updateProfileDetails(userProfileRequest, responseListener, errorListener);
    }

    private void launchCategoryActivity() {
        final Intent categoryIntent = new Intent(this, ViewCardsActivity.class);
        categoryIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(categoryIntent);
    }
}
