package com.misutesu.project.bmobmodule;

import android.content.Context;

import cn.bmob.v3.Bmob;

public class BmobUtils {

    public static void init(Context context) {
        Bmob.initialize(context, BmobConfig.APP_ID);
    }
}
