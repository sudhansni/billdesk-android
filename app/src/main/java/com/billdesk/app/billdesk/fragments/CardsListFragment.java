package com.billdesk.app.billdesk.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.interfaces.FragmentChangeListener;
import com.billdesk.app.billdesk.models.BillCard;
import com.billdesk.app.billdesk.models.Provider;

import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by rajesh on 7/27/2017.
 */

public class CardsListFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bill_cards, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StickyListHeadersListView stickyList = (StickyListHeadersListView) view.findViewById(R.id.card_list);
        MyAdapter adapter = new MyAdapter();
        stickyList.setAdapter(adapter);
        ImageView settings = (ImageView) view.findViewById(R.id.settings_shortcut);
        settings.setOnClickListener(this);
        ImageView addcard = (ImageView) view.findViewById(R.id.create_card_shortcut);
        addcard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_card_shortcut:
                FragmentChangeListener listener = (FragmentChangeListener)getActivity();
                listener.replaceFragment(new CreateCardFragment());
        }

    }

    public class MyAdapter extends BaseAdapter implements StickyListHeadersAdapter {

        Provider provider = new Provider();
        Date june = Date.valueOf("2017-6-11");
        Date july = Date.valueOf("2017-7-11");
        Date august = Date.valueOf("2017-8-11");
        BillCard[] cards = {
                new BillCard(june, provider, BillCard.PaymentStatus.PAID, 200, android.R.color.holo_red_light),
                new BillCard(june, provider, BillCard.PaymentStatus.OVERDUE, 300, android.R.color.holo_blue_light),
                new BillCard(june, provider, BillCard.PaymentStatus.PENDING, 200, android.R.color.holo_purple),
                new BillCard(july, provider, BillCard.PaymentStatus.PAID, 500, android.R.color.holo_orange_light),
                new BillCard(july, provider, BillCard.PaymentStatus.OVERDUE, 200, android.R.color.holo_green_dark),
                new BillCard(july, provider, BillCard.PaymentStatus.PENDING, 493.21f, android.R.color.holo_red_light),
                new BillCard(august, provider, BillCard.PaymentStatus.PAID, 451, android.R.color.holo_blue_light),
                new BillCard(august, provider, BillCard.PaymentStatus.OVERDUE, 452.29f, android.R.color.holo_red_light),
                new BillCard(august, provider, BillCard.PaymentStatus.PENDING, 200, android.R.color.holo_purple),
        };

        private String[] countries;
        private LayoutInflater inflater;

        public MyAdapter() {
            inflater = getActivity().getLayoutInflater();
        }

        @Override
        public int getCount() {
            return cards.length;
        }

        @Override
        public Object getItem(int position) {
            return cards[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            BillCard card = cards[position];

            ViewHolder holder;

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.bill_card, parent, false);
                holder.date = (TextView) convertView.findViewById(R.id.date);
                holder.month = (TextView) convertView.findViewById(R.id.month);
                holder.amount = (TextView) convertView.findViewById(R.id.amount);
                holder.title = (TextView) convertView.findViewById(R.id.title_and_image);
                holder.status = (TextView) convertView.findViewById(R.id.status);
                holder.accentBar = convertView.findViewById(R.id.accent_bar);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(cards[position].getDueDate());
            int extracted_date = calendar.get(Calendar.DAY_OF_MONTH);

            holder.date.setText(String.valueOf(extracted_date));
            holder.date.setTextColor(ContextCompat.getColor(getActivity(), card.getColorId()));

            SimpleDateFormat month_date = new SimpleDateFormat("MMMM", Locale.ENGLISH);
            holder.month.setText(month_date.format(calendar.getTime()));
            holder.month.setTextColor(ContextCompat.getColor(getActivity(), card.getColorId()));

            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
            holder.amount.setText(String.valueOf(formatter.format(cards[position].getAmount())));
            holder.title.setText("placeholder");
            holder.status.setText(cards[position].getStatus().toString());
            holder.status.setTextColor(ContextCompat.getColor(getActivity(), card.getColorId()));

            holder.accentBar.setBackgroundColor(ContextCompat.getColor(getActivity(), card.getColorId()));

            return convertView;
        }

        @Override
        public View getHeaderView(int position, View convertView, ViewGroup parent) {
            HeaderViewHolder holder;
            if (convertView == null) {
                holder = new HeaderViewHolder();
                convertView = inflater.inflate(R.layout.header, parent, false);
                holder.text = (TextView) convertView.findViewById(R.id.header);
                convertView.setTag(holder);
            } else {
                holder = (HeaderViewHolder) convertView.getTag();
            }
            //set header text as first char in name

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(cards[position].getDueDate());
            SimpleDateFormat month_date = new SimpleDateFormat("MMMM", Locale.ENGLISH);

            String date = month_date.format(calendar.getTime()) + " " + String.valueOf(calendar.get(Calendar.YEAR));
            holder.text.setText(date);

            return convertView;
        }

        @Override
        public long getHeaderId(int position) {
            //return the first character of the country as ID because this is what headers are based upon
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(cards[position].getDueDate());
            return 100*calendar.get(Calendar.MONTH) + calendar.get(Calendar.DAY_OF_MONTH);
        }

        class HeaderViewHolder {
            TextView text;
        }

        class ViewHolder {
            TextView date;
            TextView month;
            TextView amount;
            TextView title;
            TextView status;
            View accentBar;
        }

    }
}
