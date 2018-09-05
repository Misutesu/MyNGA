package com.misutesu.project.mynga.application;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.misutesu.project.bmob_module.BmobUtils;
import com.misutesu.project.lib_base.utils.UiUtils;
import com.misutesu.project.mynga.BuildConfig;

import timber.log.Timber;

public class NGAApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.init(this);
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        if (BuildConfig.LOG_DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        BmobUtils.init(this);

        UiUtils.init(this);
    }
}
