package com.misutesu.project.mynga.entity;

import java.io.Serializable;

public class Post implements Serializable {

    /**
     * tid : 15007486
     * fid : -7
     * titlefont : AQAAACA
     * author : UID:5076687
     * authorid : 5076687
     * subject : [教师节]你碰到过的印象最深的老师是什么样的？
     * postdate : 1536490580
     * lastpost : 1536565973
     * lastposter : 恩基爱我雕最大
     * replies : 356
     * error :
     * type : 516
     * orginal_tid : 0
     * titlefont_api : {"color":"","bold":true,"italic":false,"underline":false,"live":false}
     * set_elm_parent : 0
     * is_set : false
     * is_set_elm : false
     * is_forum : false
     * forumname :
     */

    private int tid;
    private int fid;
    private String titlefont;
    private String author;
    private int authorid;
    private String subject;
    private int postdate;
    private int lastpost;
    private String lastposter;
    private int replies;
    private String error;
    private int type;
    private int orginal_tid;
    private TitleFont titlefont_api;
    private int set_elm_parent;
    private boolean is_set;
    private boolean is_set_elm;
    private boolean is_forum;
    private String forumname;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getTitlefont() {
        return titlefont;
    }

    public void setTitlefont(String titlefont) {
        this.titlefont = titlefont;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getPostdate() {
        return postdate;
    }

    public void setPostdate(int postdate) {
        this.postdate = postdate;
    }

    public int getLastpost() {
        return lastpost;
    }

    public void setLastpost(int lastpost) {
        this.lastpost = lastpost;
    }

    public String getLastposter() {
        return lastposter;
    }

    public void setLastposter(String lastposter) {
        this.lastposter = lastposter;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOrginal_tid() {
        return orginal_tid;
    }

    public void setOrginal_tid(int orginal_tid) {
        this.orginal_tid = orginal_tid;
    }

    public TitleFont getTitlefont_api() {
        return titlefont_api;
    }

    public void setTitlefont_api(TitleFont titlefont_api) {
        this.titlefont_api = titlefont_api;
    }

    public int getSet_elm_parent() {
        return set_elm_parent;
    }

    public void setSet_elm_parent(int set_elm_parent) {
        this.set_elm_parent = set_elm_parent;
    }

    public boolean isIs_set() {
        return is_set;
    }

    public void setIs_set(boolean is_set) {
        this.is_set = is_set;
    }

    public boolean isIs_set_elm() {
        return is_set_elm;
    }

    public void setIs_set_elm(boolean is_set_elm) {
        this.is_set_elm = is_set_elm;
    }

    public boolean isIs_forum() {
        return is_forum;
    }

    public void setIs_forum(boolean is_forum) {
        this.is_forum = is_forum;
    }

    public String getForumname() {
        return forumname;
    }

    public void setForumname(String forumname) {
        this.forumname = forumname;
    }

    @Override
    public String toString() {
        return "Post{" +
                "tid=" + tid +
                ", fid=" + fid +
                ", titlefont='" + titlefont + '\'' +
                ", author='" + author + '\'' +
                ", authorid=" + authorid +
                ", subject='" + subject + '\'' +
                ", postdate=" + postdate +
                ", lastpost=" + lastpost +
                ", lastposter='" + lastposter + '\'' +
                ", replies=" + replies +
                ", error='" + error + '\'' +
                ", type=" + type +
                ", orginal_tid=" + orginal_tid +
                ", titlefont_api=" + titlefont_api +
                ", set_elm_parent=" + set_elm_parent +
                ", is_set=" + is_set +
                ", is_set_elm=" + is_set_elm +
                ", is_forum=" + is_forum +
                ", forumname='" + forumname + '\'' +
                '}';
    }

    public static class TitleFont implements Serializable  {
        /**
         * color :
         * bold : true
         * italic : false
         * underline : false
         * live : false
         */

        private String color;
        private boolean bold;
        private boolean italic;
        private boolean underline;
        private boolean live;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public boolean isBold() {
            return bold;
        }

        public void setBold(boolean bold) {
            this.bold = bold;
        }

        public boolean isItalic() {
            return italic;
        }

        public void setItalic(boolean italic) {
            this.italic = italic;
        }

        public boolean isUnderline() {
            return underline;
        }

        public void setUnderline(boolean underline) {
            this.underline = underline;
        }

        public boolean isLive() {
            return live;
        }

        public void setLive(boolean live) {
            this.live = live;
        }

        @Override
        public String toString() {
            return "TitleFont{" +
                    "color='" + color + '\'' +
                    ", bold=" + bold +
                    ", italic=" + italic +
                    ", underline=" + underline +
                    ", live=" + live +
                    '}';
        }
    }
}
