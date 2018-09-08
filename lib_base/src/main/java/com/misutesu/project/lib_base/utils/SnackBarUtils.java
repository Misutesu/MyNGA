package com.misutesu.project.lib_base.utils;

import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

import timber.log.Timber;

public class SnackBarUtils {

    private static final String TAG = "SnackBarUtils";

    public static void show(@StringRes int strRes) {
        if (ActivityUtils.getCurrentActivity() == null) {
            Timber.tag(TAG).w("getCurrentActivity()==null when SnackBarUtils.show()");
        } else {
            show(ActivityUtils.getCurrentActivity().getString(strRes));
        }
    }

    public static void show(String msg) {
        show(msg, true);
    }

    public static void show(String msg, boolean isLong) {
        if (ActivityUtils.getCurrentActivity() == null) {
            Timber.tag(TAG).w("getCurrentActivity()==null when SnackBarUtils.show()");
        } else {
            View view = ActivityUtils.getCurrentActivity().getWindow().getDecorView().findViewById(android.R.id.content);
            Snackbar.make(view, msg, isLong ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_SHORT).show();
        }
    }
}
