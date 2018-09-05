package com.misutesu.project.lib_base.http;

import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

public class LogInterceptor implements Interceptor {

    private static final String TAG = "LogInterceptor";

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        Timber.tag(TAG).d("request: %s", request.toString());
        okhttp3.MediaType mediaType = response.body().contentType();
        Timber.tag(TAG).d("mediaType.type: %s", mediaType.toString());
        if (mediaType.equals(MediaType.parse("application/json;charset=UTF-8"))
                || mediaType.equals(MediaType.parse("text/plain;charset=UTF-8"))) {
            String content = response.body().string();
            Timber.tag(TAG).d("onResponse: %s", format(content));
            return response.newBuilder().body((okhttp3.ResponseBody.create(mediaType, content))).build();
        }
        return response;
    }

    private static String format(String jsonStr) {
        int level = 0;
        StringBuilder jsonForMatStr = new StringBuilder();
        for (int i = 0; i < jsonStr.length(); i++) {
            char c = jsonStr.charAt(i);
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c).append("\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c).append("\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }
        return jsonForMatStr.toString();
    }

    private static String getLevelStr(int level) {
        StringBuilder levelStr = new StringBuilder();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }
}
