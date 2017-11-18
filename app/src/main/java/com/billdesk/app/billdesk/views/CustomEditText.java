package com.billdesk.app.billdesk.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.billdesk.app.billdesk.R;


/**
 * Created by jprince on 7/9/2017.
 */

public class CustomEditText extends LinearLayout {

    View sideBar;
    TextView labelView;
    EditText editText;


    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.edit_text, this);
        sideBar = findViewById(R.id.sideBar);
        labelView = findViewById(R.id.field_label);
        editText = findViewById(R.id.text_input);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomEditText, 0, 0);

        String hint = a.getString(R.styleable.CustomEditText_hint);
        if (hint != null) {
            setHint(hint);
        }
        String label = a.getString(R.styleable.CustomEditText_label);
        if (label != null) {
            setLabel(label);
        }
        int sideBarColor = a.getColor(R.styleable.CustomEditText_sidebarColor, getContext().getResources().getColor(R.color.defaultEditTextAccent));
        setSideBarColor(sideBarColor);
        String text = a.getString(R.styleable.CustomEditText_text);
        if(text != null){
            setText(text);
        }
        int inputType = a.getInt(R.styleable.CustomEditText_android_inputType, EditorInfo.TYPE_CLASS_TEXT);
        setInputType(inputType);

        a.recycle();

    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setHint(int stringId) {
        setHint(getContext().getString(stringId));
    }

    public void setHint(String hint) {
        editText.setHint(hint);
    }

    public void setSideBarColor(int sideBarColor) {
        sideBar.setBackgroundColor(sideBarColor);
    }

    public void setLabel(int stringId) {
        labelView.setText(stringId);
    }

    public void setLabel(String label) {
        labelView.setText(label);
    }

    public void setText(int stringId) {
        editText.setText(stringId);
    }

    public void setText(String text) {
        editText.setText(text);
    }

    public void setError(String text) {
        editText.setError(text);
    }

    public Editable getText(){
        return editText.getText();
    }

    public void setMaxLength(int length) {
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(length);
        editText.setFilters(filterArray);
    }

    public void setInputType(int inputType){
        editText.setInputType(inputType);
    }
}
