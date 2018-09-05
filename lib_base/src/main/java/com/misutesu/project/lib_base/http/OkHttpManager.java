package com.misutesu.project.lib_base.http;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.OkHttpClient;

public class OkHttpManager {
    private static OkHttpClient okHttpClient;

    public static OkHttpClient getInstance() {
        if (okHttpClient == null) {
            synchronized (OkHttpManager.class) {
                if (okHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    builder.addInterceptor(new LogInterceptor());
                    okHttpClient = RetrofitUrlManager.getInstance().with(builder).build();
                }
            }
        }
        return okHttpClient;
    }
}
