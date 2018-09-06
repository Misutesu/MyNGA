package com.misutesu.project.mynga.entity;

import java.util.List;

public class AllPlate {

    private int code;
    private String msg;
    private String forum_icon_pre;
    private List<ResultBean> result;

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

    public String getForum_icon_pre() {
        return forum_icon_pre;
    }

    public void setForum_icon_pre(String forum_icon_pre) {
        this.forum_icon_pre = forum_icon_pre;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {

        private int id;
        private String name;
        private List<GroupsBean> groups;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<GroupsBean> getGroups() {
            return groups;
        }

        public void setGroups(List<GroupsBean> groups) {
            this.groups = groups;
        }

        public static class GroupsBean {

            private int id;
            private String name;
            private String info;
            private List<ForumsBean> forums;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public List<ForumsBean> getForums() {
                return forums;
            }

            public void setForums(List<ForumsBean> forums) {
                this.forums = forums;
            }

            public static class ForumsBean {

                private int id;
                private String name;
                private String info;
                private String icon;
                private boolean is_forumlist;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public boolean isIs_forumlist() {
                    return is_forumlist;
                }

                public void setIs_forumlist(boolean is_forumlist) {
                    this.is_forumlist = is_forumlist;
                }
            }
        }
    }
}
