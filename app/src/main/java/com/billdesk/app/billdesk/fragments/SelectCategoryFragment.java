package com.billdesk.app.billdesk.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.interfaces.OnCategorySelectedListener;
import com.billdesk.app.billdesk.models.Category;
import com.billdesk.app.billdesk.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

//TODO:: This class need further modification
public class SelectCategoryFragment extends Fragment implements AdapterView.OnItemClickListener{

    private RecyclerView recyclerView;
    private AppCompatTextView emptyView;
    private List<Category> category;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_with_emptyview, null);
        recyclerView = view.findViewById(R.id.recyclerView);
        emptyView = view.findViewById(R.id.emptyView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       // category =  getArguments().getParcelableArrayList("new");
        if (category == null) {
            category = new ArrayList<>();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        OnCategorySelectedListener listener = (OnCategorySelectedListener)getParentFragment();
        listener.onCategorySelected(position);
    }

    private class CategoryAdapter extends BaseAdapter {


        private final List<Category> data;
        private Context context;
        private int resID;

        CategoryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Category> list) {
            super();
            this.context = context;
            this.resID = resource;
            this.data = list;
        }


        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return data.get(position).getCategoryId();
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
            TextView textView = convertView.findViewById(R.id.item);
            textView.setText(data.get(position).getCategoryName());
            return convertView;
        }
    }
}
