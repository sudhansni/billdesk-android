package com.billdesk.app.billdesk.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.adapters.ViewCardsAdapter;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;


public class CardsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StickyListHeadersListView stickyList = findViewById(R.id.card_list);
        ViewCardsAdapter adapter = new ViewCardsAdapter(this);
        stickyList.setAdapter(adapter);
    }

    public void onAddButtonClicked(View view) {

    }


}
