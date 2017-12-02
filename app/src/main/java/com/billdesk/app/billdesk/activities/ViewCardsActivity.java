package com.billdesk.app.billdesk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.adapters.ViewCardsAdapter;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;


public class ViewCardsActivity extends BaseActivity {

    private TextView emptyTextView;
    private ViewCardsAdapter adapter;
    private StickyListHeadersListView stickyList;
    public static final int REQUEST_CODE_ADD_CARD = 101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        stickyList = findViewById(R.id.card_list);
        adapter = new ViewCardsAdapter(this);
        stickyList.setAdapter(adapter);

        emptyTextView = findViewById(R.id.empty_textview);
        updateUi();
    }

    public void onAddButtonClicked(View view) {
        // Launch Add Card Activity
        Intent addCardActivityIntent = new Intent(this, AddCardActivity.class);
        startActivityForResult(addCardActivityIntent, REQUEST_CODE_ADD_CARD);
    }

    private void updateUi() {
        if (adapter.getCount() == 0) {
            emptyTextView.setVisibility(View.VISIBLE);
            stickyList.setVisibility(View.GONE);

        } else {
            emptyTextView.setVisibility(View.GONE);
            stickyList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
