package com.misutesu.project.mynga.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "collect_plat")
public class Plate implements Serializable {

    @PrimaryKey
    private int id;

    private String name;

    private String info;

    private String icon;

    @Ignore
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
