package com.billdesk.app.billdesk.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.fragments.CardsListFragment;
import com.billdesk.app.billdesk.interfaces.FragmentChangeListener;

/**
 * Created by rajesh on 7/23/2017.
 */

public class CardsActivity extends AppCompatActivity implements FragmentChangeListener{

    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_root, fragment);
        transaction.commit();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        CardsListFragment fragment = new CardsListFragment();
        transaction.add(R.id.fragment_root, fragment);
        transaction.commit();
    }


}
