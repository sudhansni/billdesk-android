package com.billdesk.app.billdesk.adapters;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.activities.CardsActivity;
import com.billdesk.app.billdesk.interfaces.PaymentStatus;
import com.billdesk.app.billdesk.models.BillCard;
import com.billdesk.app.billdesk.models.Provider;

import java.sql.Date;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class ViewCardsAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private CardsActivity cardsActivity;
    Provider provider = new Provider("Telstra Corporation");

    Date june = Date.valueOf("2017-6-11");
    Date july = Date.valueOf("2017-7-11");
    Date august = Date.valueOf("2017-8-11");
    BillCard[] cards = {
            new BillCard(june, provider, PaymentStatus.PAID, 200, android.R.color.holo_red_light),
            new BillCard(june, provider, PaymentStatus.OVERDUE, 300, android.R.color.holo_blue_light),
            new BillCard(june, provider, PaymentStatus.PENDING, 200, android.R.color.holo_purple),
            new BillCard(july, provider, PaymentStatus.PAID, 500, android.R.color.holo_orange_light),
            new BillCard(july, provider, PaymentStatus.OVERDUE, 200, android.R.color.holo_green_dark),
            new BillCard(july, provider, PaymentStatus.PENDING, 493.21f, android.R.color.holo_red_light),
            new BillCard(august, provider, PaymentStatus.PAID, 451, android.R.color.holo_blue_light),
            new BillCard(august, provider, PaymentStatus.OVERDUE, 452.29f, android.R.color.holo_red_light),
            new BillCard(august, provider, PaymentStatus.PENDING, 200, android.R.color.holo_purple),
    };

    private String[] countries;
    private LayoutInflater inflater;

    public ViewCardsAdapter(CardsActivity cardsActivity) {
        this.cardsActivity = cardsActivity;
        inflater = cardsActivity.getLayoutInflater();
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
            holder.date = convertView.findViewById(R.id.textview_card_due_value);
            holder.amount = convertView.findViewById(R.id.textview_card_amount_value);
            holder.title = convertView.findViewById(R.id.textview_card_title);
            holder.status = convertView.findViewById(R.id.textview_card_status_value);
            holder.accentBar = convertView.findViewById(R.id.accent_bar);
            convertView.setTag(holder);
        } else {
            holder = (ViewCardsViewHolder) convertView.getTag();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cards[position].getDueDate());
        int extracted_date = calendar.get(Calendar.DAY_OF_MONTH);

        holder.date.setText(String.valueOf(extracted_date));

        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        holder.amount.setText(String.valueOf(formatter.format(cards[position].getAmount())));
        holder.title.setText(card.getProvider().getVcProviderName());
        holder.status.setText(card.getStatus().toString());
        holder.status.setTextColor(ContextCompat.getColor(cardsActivity, card.getColorId()));

        holder.accentBar.setBackgroundColor(ContextCompat.getColor(cardsActivity, card.getColorId()));

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.header, parent, false);
            holder.text = convertView.findViewById(R.id.header);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        holder.text.setText(cards[position].getProvider().getVcProviderName());
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        //return the first character of the country as ID because this is what headers are based upon
        return cards[position].getProvider().getVcProviderName().charAt(0);
    }

    private static class HeaderViewHolder {
        TextView text;
    }

    private static class ViewCardsViewHolder {
        TextView date;
        TextView amount;
        TextView title;
        TextView status;
        View accentBar;
    }

}
