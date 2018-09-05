package com.misutesu.project.lib_base.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.AttrRes;
import android.util.TypedValue;

public class ThemeUtils {
    public static int getColor(Context context, @AttrRes int id) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(id, typedValue, true);
        return typedValue.data;
    }
}
