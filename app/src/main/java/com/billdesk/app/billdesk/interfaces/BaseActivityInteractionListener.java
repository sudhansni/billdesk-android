package com.billdesk.app.billdesk.interfaces;

public interface BaseActivityInteractionListener {

    void showProgress();

    void hideProgress();

    boolean isNetworkConnected();

    void showError(String title, String message);

    void showGenericError();

    void showSuccess(String title, String message);

}
