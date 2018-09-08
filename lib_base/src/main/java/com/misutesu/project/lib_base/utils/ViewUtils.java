package com.misutesu.project.lib_base.utils;

import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.TypedValue;
import android.view.View;

public class ViewUtils {
    public static void setBorderlessBg(View view){
        TypedValue typedValue = new TypedValue();
        int res;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            res = android.R.attr.selectableItemBackgroundBorderless;
        } else {
            res = android.R.attr.selectableItemBackground;
        }
        view.getContext().getTheme().resolveAttribute(res, typedValue, true);
        int[] attribute = new int[]{res};
        TypedArray typedArray = view.getContext().getTheme().obtainStyledAttributes(typedValue.resourceId, attribute);
        ViewCompat.setBackground(view, typedArray.getDrawable(0));
    }
}
