package com.misutesu.project.mynga.config;

import android.util.SparseArray;

import com.misutesu.project.mynga.entity.Server;

import java.util.List;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

public class ServerConfig {

    public static final int SERVER_API = 1;
    public static final int SERVER_ICON = 2;

    private static SparseArray<String> mServer = new SparseArray<>();

    public static void init() {
        mServer.put(SERVER_API, "http://bbs.nga.cn/app_api.php");
        mServer.put(SERVER_ICON, "http://img4.nga.178.com/ngabbs/nga_classic/f/app/%s.png");

        RetrofitUrlManager.getInstance().setGlobalDomain(getUrl(SERVER_API));
    }

    public static void setServers(List<Server> list) {
        mServer.clear();
        for (Server s : list) {
            mServer.put(s.getUrlType(), s.getUrl());
        }
    }

    public static String getUrl(int type) {
        return mServer.get(type);
    }
}
