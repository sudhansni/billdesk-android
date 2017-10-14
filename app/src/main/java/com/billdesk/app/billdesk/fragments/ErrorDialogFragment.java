package com.billdesk.app.billdesk.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;

import com.billdesk.app.billdesk.views.SweetAlertDialog;


public class ErrorDialogFragment extends AppCompatDialogFragment {

    private String title;
    private String message;
    private String confirmMessage;
    private String cancelMessage;
    private SweetAlertDialog.OnSweetClickListener confirmClickListener, cancelClickListener;
    private int alertType = SweetAlertDialog.ERROR_TYPE;

    public static final String BUNDLE_ERROR_TITLE = "bundle_error_title";
    public static final String BUNDLE_ERROR_MESSAGE = "bundle_error_message";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            title = args.getString(BUNDLE_ERROR_TITLE);
            message = args.getString(BUNDLE_ERROR_MESSAGE);
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        SweetAlertDialog dialog = new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE).
                setTitleText(title).
                setContentText(message);

        dialog.setConfirmClickListener(confirmClickListener != null ? confirmClickListener : new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
            }
        });

        if (cancelClickListener != null) {
            dialog.setCancelClickListener(cancelClickListener);
        }

        if (confirmMessage != null) {
            dialog.setConfirmText(confirmMessage);
        }

        if (cancelMessage != null) {
            dialog.setCancelText(cancelMessage);
        }

        dialog.changeAlertType(alertType);


        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String getTitle() {
        return this.title;
    }

    public String getConfirmMessage() {
        return this.confirmMessage;
    }

    public void setConfirmMessage(String confirmMessage) {
        this.confirmMessage = confirmMessage;
    }

    public String getCancelMessage() {
        return this.cancelMessage;
    }

    public void setCancelMessage(String cancelMessage) {
        this.cancelMessage = cancelMessage;
    }

    public SweetAlertDialog.OnSweetClickListener getConfirmClickListener() {
        return this.confirmClickListener;
    }

    public void setConfirmClickListener(SweetAlertDialog.OnSweetClickListener confirmClickListener) {
        this.confirmClickListener = confirmClickListener;
    }

    public SweetAlertDialog.OnSweetClickListener getCancelClickListener() {
        return this.cancelClickListener;
    }

    public void setCancelClickListener(SweetAlertDialog.OnSweetClickListener cancelClickListener) {
        this.cancelClickListener = cancelClickListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public int getAlertType() {
        return this.alertType;
    }

    public void setAlertType(int alertType) {
        this.alertType = alertType;
    }
}
