package com.misutesu.project.mynga.router;

public interface DiscussRouter {
    String group = "discuss";
    String base = "/" + group;

    /**
     * 主页
     */
    String main = base + "/main";

    /**
     * 帖子列表
     */
    String post_list = base + "/post_list";
}
