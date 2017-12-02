package com.billdesk.app.billdesk.adapters;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.activities.ViewCardsActivity;
import com.billdesk.app.billdesk.models.BillCard;
import com.billdesk.app.billdesk.models.Provider;

import java.sql.Date;
import java.text.NumberFormat;
import java.util.Locale;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class ViewCardsAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private ViewCardsActivity viewCardsActivity;
    Provider provider = new Provider("Telstra Corporation");

    Date june = Date.valueOf("2017-6-11");
    Date july = Date.valueOf("2017-7-11");
    Date august = Date.valueOf("2017-8-11");
//    BillCard[] cards = {
//            new BillCard(june, provider, PaymentStatus.PAID, 200),
//            new BillCard(june, provider, PaymentStatus.OVERDUE, 300),
//            new BillCard(june, provider, PaymentStatus.PENDING, 200),
//            new BillCard(july, provider, PaymentStatus.PAID, 500),
//            new BillCard(july, provider, PaymentStatus.OVERDUE, 200),
//            new BillCard(july, provider, PaymentStatus.PENDING, 493.21f),
//            new BillCard(august, provider, PaymentStatus.PAID, 451),
//            new BillCard(august, provider, PaymentStatus.OVERDUE, 452.29f),
//            new BillCard(august, provider, PaymentStatus.PENDING, 200),
//    };
    BillCard[] cards = new BillCard[0];

    private String[] countries;
    private LayoutInflater inflater;

    public ViewCardsAdapter(ViewCardsActivity viewCardsActivity) {
        this.viewCardsActivity = viewCardsActivity;
        inflater = viewCardsActivity.getLayoutInflater();
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

        ViewCardsViewHolder holder;

        if (convertView == null) {
            holder = new ViewCardsViewHolder();
            convertView = inflater.inflate(R.layout.bill_card, parent, false);
            holder.dueOnTextView = convertView.findViewById(R.id.textview_card_due_value);
            holder.dueAmountTextView = convertView.findViewById(R.id.textview_card_amount_value);
            holder.providerTextView = convertView.findViewById(R.id.textview_card_title);
            holder.statusTextView = convertView.findViewById(R.id.textview_card_status_value);
            holder.accentBar = convertView.findViewById(R.id.accent_bar);
            convertView.setTag(holder);
        } else {
            holder = (ViewCardsViewHolder) convertView.getTag();
        }

        holder.dueOnTextView.setText(card.getDueDate().toString());

        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        holder.dueAmountTextView.setText(String.valueOf(formatter.format(cards[position].getAmount())));
        holder.providerTextView.setText(card.getProvider().getVcProviderName());
        holder.statusTextView.setText(card.getStatus().name());
        holder.statusTextView.setTextColor(ContextCompat.getColor(viewCardsActivity, card.getStatus().getColor()));

        holder.accentBar.setBackgroundColor(ContextCompat.getColor(viewCardsActivity, card.getStatus().getColor()));

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.header, parent, false);
            holder.categoryTextView = convertView.findViewById(R.id.header);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        holder.categoryTextView.setText(cards[position].getProvider().getVcProviderName());
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        //return the first character of the country as ID because this is what headers are based upon
        return cards[position].getProvider().getVcProviderName().charAt(0);
    }

    private static class HeaderViewHolder {
        TextView categoryTextView;
    }

    private static class ViewCardsViewHolder {
        TextView dueOnTextView;
        TextView dueAmountTextView;
        TextView providerTextView;
        TextView statusTextView;
        View accentBar;
    }

}
