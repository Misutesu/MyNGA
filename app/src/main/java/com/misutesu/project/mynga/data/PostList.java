package com.misutesu.project.mynga.data;

import com.misutesu.project.mynga.entity.Post;
import com.misutesu.project.mynga.entity.PostListTitle;

import java.util.List;

public class PostList {

    /**
     * code : 0
     * msg : 操作成功
     * result : {...}
     * guest_token : guest05b962b10a4860
     * totalPage : 95134
     * total : 3329686
     * currentPage : 1
     * perPage : 35
     * forumname : 网事杂谈
     */

    private int code;
    private String msg;
    private ResultBean result;
    private String guest_token;
    private int totalPage;
    private int total;
    private int currentPage;
    private int perPage;
    private String forumname;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getGuest_token() {
        return guest_token;
    }

    public void setGuest_token(String guest_token) {
        this.guest_token = guest_token;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public String getForumname() {
        return forumname;
    }

    public void setForumname(String forumname) {
        this.forumname = forumname;
    }

    public static class ResultBean {

        private PostListTitle.PlateHeader header;
        private List<PostListTitle.ChildPlate> subForum;
        private List<Post> data;
        private List<Integer> unionforum_ary_all;

        public PostListTitle.PlateHeader getHeader() {
            return header;
        }

        public void setHeader(PostListTitle.PlateHeader header) {
            this.header = header;
        }

        public List<PostListTitle.ChildPlate> getSubForum() {
            return subForum;
        }

        public void setSubForum(List<PostListTitle.ChildPlate> subForum) {
            this.subForum = subForum;
        }

        public List<Post> getData() {
            return data;
        }

        public void setData(List<Post> data) {
            this.data = data;
        }

        public List<Integer> getUnionforum_ary_all() {
            return unionforum_ary_all;
        }

        public void setUnionforum_ary_all(List<Integer> unionforum_ary_all) {
            this.unionforum_ary_all = unionforum_ary_all;
        }
    }
}
