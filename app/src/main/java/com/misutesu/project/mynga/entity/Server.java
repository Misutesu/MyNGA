package com.misutesu.project.mynga.entity;

import cn.bmob.v3.BmobObject;

public class Server extends BmobObject {
    private int urlType;
    private String url;

    public int getUrlType() {
        return urlType;
    }

    public void setUrlType(int urlType) {
        this.urlType = urlType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Server{" +
                "urlType=" + urlType +
                ", url='" + url + '\'' +
                '}';
    }
}
