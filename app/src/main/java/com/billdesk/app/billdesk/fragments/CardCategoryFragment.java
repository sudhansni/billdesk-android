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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.interfaces.OnCategorySelectedListener;
import com.billdesk.app.billdesk.models.Category;
import com.billdesk.app.billdesk.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;


public class CardCategoryFragment extends Fragment implements AdapterView.OnItemClickListener{

    private ListView listView;
    private List<Category> category;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.category_list_fragment, null);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        category =  getArguments().getParcelableArrayList("new");
        if (category == null) {
            category = new ArrayList<>();
        }

        listView = (ListView) view.findViewById(R.id.list_view_categories);
        listView.setOnItemClickListener(this);
        listView.setAdapter(new CategoryAdapter(getActivity(), R.layout.category_list_item, category));


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
            TextView textView = (TextView) convertView.findViewById(R.id.item);
            textView.setText(data.get(position).getCategoryName());
            return convertView;
        }
    }
}
