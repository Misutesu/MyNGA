package com.misutesu.project.mynga.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TimeUtils {

    private static final String FORMAT = "yyyy年MM月dd日 HH:mm:ss";

    public static String getDistanceTime(long time) {
        StringBuilder timeStr = new StringBuilder();
        int seconds = (int) (System.currentTimeMillis() - time) / 1000;
        if (seconds < 60) {
            timeStr.append(seconds).append("秒前");
        } else if (seconds < 60 * 60) {
            timeStr.append(seconds / 60).append("分钟前");
        } else if (seconds < 24 * 60 * 60) {
            timeStr.append(seconds / 60 / 60).append("小时前");
        } else if (seconds < 3 * 24 * 60 * 60) {
            timeStr.append(seconds / 60 / 60 / 24).append("天前");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT, Locale.CHINA);
            return sdf.format(time);
        }
        return timeStr.toString();
    }
}
