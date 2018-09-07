package com.misutesu.project.lib_base.utils;

import java.util.Locale;

public class StringUtils {

    public static String format(String str, Object... args) {
        return String.format(Locale.getDefault(), str, args);
    }
}
