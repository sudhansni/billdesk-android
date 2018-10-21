package com.billdesk.app.billdesk.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.interfaces.OnCategorySelectedListener;
import com.billdesk.app.billdesk.models.GetConfigResponse;

public class CreateCardFragment extends Fragment implements View.OnClickListener, OnCategorySelectedListener {

    private GetConfigResponse config;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_card, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCategorySelected(int position) {

    }

}
