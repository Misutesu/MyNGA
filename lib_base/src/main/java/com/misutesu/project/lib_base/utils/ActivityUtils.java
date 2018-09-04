package com.misutesu.project.lib_base.utils;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ActivityUtils {
    private static List<WeakReference<Activity>> activities;

    public static void add(Activity activity) {
        if (activities == null) {
            activities = new ArrayList<>();
        }
        activities.add(new WeakReference<>(activity));
    }

    public static void remove(Activity activity) {
        for (WeakReference<Activity> a : activities) {
            if (a.get() == activity) {
                activities.remove(a);
                return;
            }
        }
    }
}
