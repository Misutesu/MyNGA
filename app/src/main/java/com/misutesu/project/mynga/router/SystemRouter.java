package com.misutesu.project.mynga.router;

public interface SystemRouter {
    String group = "system";
    String base = "/" + group;

    /**
     * 初始化
     */
    String init = "/init";
}
