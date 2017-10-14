package com.billdesk.app.billdesk.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.billdesk.app.billdesk.R;
import com.billdesk.app.billdesk.fragments.ErrorDialogFragment;
import com.billdesk.app.billdesk.fragments.ProgressDialogFragment;
import com.billdesk.app.billdesk.interfaces.BaseActivityInteractionListener;
import com.billdesk.app.billdesk.interfaces.EditTextFocusChangeListener;
import com.billdesk.app.billdesk.utils.UiUtils;
import com.billdesk.app.billdesk.views.SweetAlertDialog;

import static com.billdesk.app.billdesk.fragments.ErrorDialogFragment.BUNDLE_ERROR_MESSAGE;
import static com.billdesk.app.billdesk.fragments.ErrorDialogFragment.BUNDLE_ERROR_TITLE;


public abstract class BaseActivity extends AppCompatActivity implements BaseActivityInteractionListener {

    private static final String PROGRESS_FRAGMENT_TAG = ProgressDialogFragment.class.getName();
    private static final String ERROR_FRAGMENT_TAG = ErrorDialogFragment.class.getName();
    private static final String BUNDLE_IS_PROGRESS_SHOWING = "bundle_is_progress_showing";
    private static final String BUNDLE_IS_ERROR_SHOWING = "bundle_is_error_showing";
    protected EditTextFocusChangeListener editTextFocusChangeListener;
    private ProgressDialogFragment progressDialogFragment;
    private ErrorDialogFragment errorDialogFragment;
    protected Response.ErrorListener errorListener;
    private SweetAlertDialog.OnSweetClickListener finishOnConfirmListener = new SweetAlertDialog.OnSweetClickListener() {
        @Override
        public void onClick(SweetAlertDialog sweetAlertDialog) {
            setResult(RESULT_OK);
            finish();
        }
    };

    private SweetAlertDialog.OnSweetClickListener dismissOnConfirmListener = new SweetAlertDialog.OnSweetClickListener() {
        @Override
        public void onClick(SweetAlertDialog sweetAlertDialog) {
            sweetAlertDialog.dismissWithAnimation();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideProgress();
                showGenericError();
                error.printStackTrace();
            }
        };

        setupFragments();

        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean(BUNDLE_IS_PROGRESS_SHOWING)) {
                showProgress();
            }

            if (savedInstanceState.getBoolean(BUNDLE_IS_ERROR_SHOWING)) {
                showError(savedInstanceState.getString(BUNDLE_ERROR_TITLE), savedInstanceState.getString(BUNDLE_ERROR_MESSAGE));
            }

        }
        editTextFocusChangeListener = new EditTextFocusChangeListener();
    }

    private void setupFragments() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(PROGRESS_FRAGMENT_TAG);
        if (fragment == null) {
            progressDialogFragment = new ProgressDialogFragment();
        } else {
            progressDialogFragment = (ProgressDialogFragment) fragment;
        }

        fragment = getSupportFragmentManager().findFragmentByTag(ERROR_FRAGMENT_TAG);
        if (fragment == null) {
            errorDialogFragment = new ErrorDialogFragment();
        } else {
            errorDialogFragment = (ErrorDialogFragment) fragment;
        }

    }

    @Override
    public void showProgress() {
        progressDialogFragment.show(getSupportFragmentManager(), PROGRESS_FRAGMENT_TAG);
    }

    @Override
    public void hideProgress() {
        progressDialogFragment.dismiss();
    }

    @Override
    public boolean isNetworkConnected() {
        if (UiUtils.isNetworkConnected(this)) {
            return true;
        } else {
            showError(getString(R.string.error_dialog_title), getString(R.string.error_desc_no_internet));
            return false;
        }
    }

    @Override
    public void showError(String title, String message) {
        if (errorDialogFragment.getDialog() != null && errorDialogFragment.getDialog().isShowing()) {
            errorDialogFragment.dismissAllowingStateLoss();
        }
        errorDialogFragment.setTitle(title);
        errorDialogFragment.setMessage(message);
        errorDialogFragment.show(getSupportFragmentManager(), ERROR_FRAGMENT_TAG);

    }

    @Override
    public void showGenericError() {
        showError(getString(R.string.generic_error_title), getString(R.string.generic_error_desc));
    }

    @Override
    public void showSuccess(final String title, final String message) {
        showSuccess(title, message, false);
    }

    protected void showSuccess(final String title, final String message, final boolean finishOnConfirm) {
        errorDialogFragment.setAlertType(SweetAlertDialog.SUCCESS_TYPE);
        errorDialogFragment.setTitle(title);
        errorDialogFragment.setMessage(message);
        errorDialogFragment.setConfirmClickListener(finishOnConfirm ? finishOnConfirmListener : dismissOnConfirmListener);
        errorDialogFragment.setCancelMessage(null);
        errorDialogFragment.setConfirmMessage(getString(android.R.string.ok));
        errorDialogFragment.setCancelClickListener(null);
        errorDialogFragment.show(getSupportFragmentManager(), ERROR_FRAGMENT_TAG);
    }

    protected void showError(String title, String message, String confirmMsg, String cancelMsg, SweetAlertDialog.OnSweetClickListener confirmListener, SweetAlertDialog.OnSweetClickListener cancelListener) {
        dismissErrorDialog();
        errorDialogFragment.setTitle(title);
        errorDialogFragment.setAlertType(SweetAlertDialog.ERROR_TYPE);
        errorDialogFragment.setMessage(message);
        errorDialogFragment.setCancelMessage(cancelMsg);
        errorDialogFragment.setConfirmMessage(confirmMsg);
        errorDialogFragment.setConfirmClickListener(confirmListener);
        errorDialogFragment.setCancelClickListener(cancelListener);
        errorDialogFragment.show(getSupportFragmentManager(), ERROR_FRAGMENT_TAG);

    }

    protected void dismissErrorDialog() {
        if (errorDialogFragment.isAdded() || errorDialogFragment.isVisible()) {
            errorDialogFragment.dismissAllowingStateLoss();
        }
    }

    protected void showDialog(int alertType, String title, String message, boolean finishOnConfirm) {
        dismissErrorDialog();
        errorDialogFragment.setTitle(title);
        errorDialogFragment.setAlertType(alertType);
        errorDialogFragment.setMessage(message);

        errorDialogFragment.setConfirmClickListener(finishOnConfirm ? finishOnConfirmListener : dismissOnConfirmListener);
        errorDialogFragment.setCancelMessage(null);
        errorDialogFragment.setConfirmMessage(getString(android.R.string.ok));
        errorDialogFragment.setCancelClickListener(null);
        errorDialogFragment.show(getSupportFragmentManager(), ERROR_FRAGMENT_TAG);
    }

    protected void showWarning(String title, String message, String confirmMsg, String cancelMsg, SweetAlertDialog.OnSweetClickListener confirmListener, SweetAlertDialog.OnSweetClickListener cancelListener) {
        dismissErrorDialog();
        errorDialogFragment.setTitle(title);
        errorDialogFragment.setAlertType(SweetAlertDialog.WARNING_TYPE);
        errorDialogFragment.setMessage(message);
        errorDialogFragment.setCancelMessage(cancelMsg);
        errorDialogFragment.setConfirmMessage(confirmMsg);
        errorDialogFragment.setConfirmClickListener(confirmListener);
        errorDialogFragment.setCancelClickListener(cancelListener);
        errorDialogFragment.show(getSupportFragmentManager(), ERROR_FRAGMENT_TAG);

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (isFinishing()) {
            outState.putBoolean(BUNDLE_IS_PROGRESS_SHOWING, progressDialogFragment.isVisible());
            outState.putBoolean(BUNDLE_IS_ERROR_SHOWING, errorDialogFragment.isVisible());
            outState.putString(BUNDLE_ERROR_TITLE, errorDialogFragment.getTitle());
            outState.putString(BUNDLE_ERROR_MESSAGE, errorDialogFragment.getMessage());
        }
    }



}
