package com.billdesk.app.billdesk.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.interfaces.FragmentChangeListener;
import com.billdesk.app.billdesk.interfaces.OnCategorySelectedListener;
import com.billdesk.app.billdesk.models.GetConfigResponse;
import com.billdesk.app.billdesk.services.BillDeskClient;
import com.billdesk.app.billdesk.services.ServiceUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        BillDeskClient client = ServiceUtil.getService();
        Call<GetConfigResponse> call = client.getCategories();
        call.enqueue(new Callback<GetConfigResponse>() {
            @Override
            public void onResponse(Call<GetConfigResponse> call, Response<GetConfigResponse> response) {
                config = response.body();
                if(config != null && config.getCategories() != null) {
                    FragmentManager manager = getChildFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    Fragment fragment = new CardCategoryFragment();
                    Bundle bundle = new Bundle();
                   // bundle.putParcelableArrayList("new", config.getCategories());
                    fragment.setArguments(bundle);
                    transaction.add(R.id.create_card_placeholder, fragment);
                    transaction.commit();
                }
            }

            @Override
            public void onFailure(Call<GetConfigResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "failed", Toast.LENGTH_LONG).show();
            }
        });

        ImageView settings = view.findViewById(R.id.settings_shortcut);
        settings.setOnClickListener(this);
        ImageView showCards = view.findViewById(R.id.card_view_shortcut);
        showCards.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_view_shortcut:
                FragmentChangeListener listener = (FragmentChangeListener) getActivity();
            //    listener.replaceFragment(new CardsListFragment());
        }
    }

    @Override
    public void onCategorySelected(int position) {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = new CardCategoryFragment();
        Bundle bundle = new Bundle();
      //  bundle.putParcelableArrayList("new", config.getCategories());
        fragment.setArguments(bundle);
        transaction.add(R.id.create_card_placeholder, fragment);
        transaction.commit();
    }

}
