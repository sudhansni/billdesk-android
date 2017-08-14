package com.billdesk.app.billdesk.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.utils.UiUtils;
import com.billdesk.app.billdesk.interfaces.FragmentChangeListener;
import com.billdesk.app.billdesk.models.Category;
import com.billdesk.app.billdesk.models.GetConfigResponse;
import com.billdesk.app.billdesk.services.BillDeskClient;
import com.billdesk.app.billdesk.services.ServiceUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rajesh on 7/27/2017.
 */

public class CreateCardFragment extends Fragment implements View.OnClickListener {

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_card, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.list_view_cateegories);

        BillDeskClient client = ServiceUtil.getService();
        Call<GetConfigResponse> call = client.getCategories();
        call.enqueue(new Callback<GetConfigResponse>() {
            @Override
            public void onResponse(Call<GetConfigResponse> call, Response<GetConfigResponse> response) {
                GetConfigResponse config = response.body();
                if(config != null && config.getCategories() != null) {
                    listView.setAdapter(new CategoryAdapter(getActivity(), R.layout.category_list_item, config.getCategories()));
                }
            }

            @Override
            public void onFailure(Call<GetConfigResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "failed", Toast.LENGTH_LONG).show();
            }
        });

        ImageView settings = (ImageView) view.findViewById(R.id.settings_shortcut);
        settings.setOnClickListener(this);
        ImageView showCards = (ImageView) view.findViewById(R.id.card_view_shortcut);
        showCards.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_view_shortcut:
                FragmentChangeListener listener = (FragmentChangeListener) getActivity();
                listener.replaceFragment(new CardsListFragment());
        }
    }

    private class CategoryAdapter extends ArrayAdapter<Category> {


        private final List<Category> data;
        private Context context;
        private int resID;

        public CategoryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Category> list) {
            super(context, resource, list);
            this.context = context;
            this.resID = resource;
            this.data = list;
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                convertView = inflater.inflate(resID, parent, false);
            }

            View view = convertView.findViewById(R.id.sideBar);
            view.setBackgroundResource(UiUtils.getRandomAccentColor());
            TextView textView = (TextView) convertView.findViewById(R.id.item);
            textView.setText(data.get(position).getCategoryName());
            return convertView;
        }
    }
}
