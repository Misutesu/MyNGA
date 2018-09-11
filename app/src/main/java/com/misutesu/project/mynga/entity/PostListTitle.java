package com.misutesu.project.mynga.entity;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PostListTitle {

    public static final int TOP = 0;
    public static final int HOT = 1;
    public static final int ESSENCE = 2;
    public static final int HEADER = 3;
    public static final int CHILD = 4;

    private List<ChildPlate> childPlates;
    private PlateHeader plateHeader;

    public List<ChildPlate> getChildPlates() {
        return childPlates;
    }

    public void setChildPlates(List<ChildPlate> childPlates) {
        this.childPlates = childPlates;
    }

    public PlateHeader getPlateHeader() {
        return plateHeader;
    }

    public void setPlateHeader(PlateHeader plateHeader) {
        this.plateHeader = plateHeader;
    }

    public class PlateHeader implements Serializable {
        /**
         * title : 版头
         * opentype : 1
         * opendata : 3593852
         */

        private String title;
        private int opentype = -1;
        private int opendata = -1;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getOpentype() {
            return opentype;
        }

        public void setOpentype(int opentype) {
            this.opentype = opentype;
        }

        public int getOpendata() {
            return opendata;
        }

        public void setOpendata(int opendata) {
            this.opendata = opendata;
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(title);
        }
    }

    public class ChildPlate implements Serializable {
        /**
         * 0 : 588
         * 1 : 15周年，企鹅帽上车互助组队合集！
         * 2 :
         * id : 588
         * name : 15周年，企鹅帽上车互助组队合集！
         * info :
         * checked : 0
         * allow_checked : 0
         * sub_type : 0
         * tid : 0
         * 4 : 7
         */

        @SerializedName("0")
        private int _$0;
        @SerializedName("1")
        private String _$1;
        @SerializedName("2")
        private String _$2;
        private int id;
        private String name;
        private String info;
        private int checked;
        private int allow_checked;
        private int sub_type;
        private int tid;
        @SerializedName("4")
        private int _$4;

        public int get_$0() {
            return _$0;
        }

        public void set_$0(int _$0) {
            this._$0 = _$0;
        }

        public String get_$1() {
            return _$1;
        }

        public void set_$1(String _$1) {
            this._$1 = _$1;
        }

        public String get_$2() {
            return _$2;
        }

        public void set_$2(String _$2) {
            this._$2 = _$2;
        }

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

        public int getChecked() {
            return checked;
        }

        public void setChecked(int checked) {
            this.checked = checked;
        }

        public int getAllow_checked() {
            return allow_checked;
        }

        public void setAllow_checked(int allow_checked) {
            this.allow_checked = allow_checked;
        }

        public int getSub_type() {
            return sub_type;
        }

        public void setSub_type(int sub_type) {
            this.sub_type = sub_type;
        }

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }

        public int get_$4() {
            return _$4;
        }

        public void set_$4(int _$4) {
            this._$4 = _$4;
        }
    }
}
