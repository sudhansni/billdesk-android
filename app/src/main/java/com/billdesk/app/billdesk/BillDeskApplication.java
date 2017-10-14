package com.billdesk.app.billdesk;

import android.app.Application;

import com.billdesk.app.billdesk.network.NetworkManager;
import com.billdesk.app.billdesk.preferences.BillDeskPreferences;

public class BillDeskApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize network manager
        NetworkManager.initialize(this);

        // Init Preferences
        BillDeskPreferences.initialize(this);
    }
}
