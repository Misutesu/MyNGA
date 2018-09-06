package com.misutesu.project.lib_base.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;

import timber.log.Timber;

public class ToastUtils {
    private static final String TAG = "ToastUtils";

    public static void show(@StringRes int strRes) {
        if (ActivityUtils.getCurrentActivity() == null) {
            Timber.tag(TAG).w("getCurrentActivity()==null when ToastUtils.show()");
        } else {
            show(ActivityUtils.getCurrentActivity().getString(strRes));
        }
    }

    public static void show(String msg) {
        if (ActivityUtils.getCurrentActivity() == null) {
            Timber.tag(TAG).w("getCurrentActivity()==null when ToastUtils.show()");
        } else {
            Toast.makeText(ActivityUtils.getCurrentActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}
