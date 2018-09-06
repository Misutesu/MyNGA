package com.misutesu.project.lib_base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.AttrRes;
import android.support.v7.app.AppCompatDelegate;
import android.util.TypedValue;

import com.misutesu.project.lib_base.R;

import java.util.ArrayList;
import java.util.List;

public class ThemeUtils {

    private static final String SP_NAME = "Theme";
    private static List<Integer> styles = new ArrayList<>();

    private static SharedPreferences preferences;

    public static int getColor(Context context, @AttrRes int id) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(id, typedValue, true);
        return typedValue.data;
    }

    public static void init(Context context) {
        if (preferences == null) {
            synchronized (ThemeUtils.class) {
                if (preferences == null) {
                    preferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
                    styles.clear();
                    styles.add(R.style.BlueTheme);
                    styles.add(R.style.BlueBlackTheme);
                    styles.add(R.style.GreenTheme);
                    styles.add(R.style.GreenBlackTheme);
                    styles.add(R.style.YellowTheme);
                    styles.add(R.style.RedTheme);
                    styles.add(R.style.PinkTheme);
                    styles.add(R.style.BrownTheme);
                    styles.add(R.style.BlackTheme);
                }
            }
        }
    }

    public static void setTheme(Activity activity) {
        AppCompatDelegate.setDefaultNightMode(getStyleIndex() == -1 ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        activity.setTheme(getStyleRes());
    }

    public static int getStyleIndex() {
        return preferences.getInt(Resources.Theme.class.getName(), 0);
    }

    public static int getStyleRes() {
        int styleIndex = getStyleIndex();
        if (styleIndex == -1) {
            return R.style.NightTheme;
        } else {
            return styles.get(getStyleIndex());
        }
    }

    public static void setStyle(int num) {
        preferences.edit().putInt(Resources.Theme.class.getName(), num).apply();
    }
}
