package com.misutesu.project.mynga.mvp.ui.diffcallback;

import android.support.v7.util.DiffUtil;
import android.text.TextUtils;

import com.misutesu.project.mynga.data.AllPlate;
import com.misutesu.project.mynga.entity.Plate;

import java.util.List;

public class PlateCallBack extends DiffUtil.Callback {

    private List mOldList, mNewList;

    public PlateCallBack(List mOldList, List mNewList) {
        this.mOldList = mOldList;
        this.mNewList = mNewList;
    }

    @Override
    public int getOldListSize() {
        return mOldList == null ? 0 : mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList == null ? 0 : mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        if (mOldList.get(oldItemPosition).getClass() != mNewList.get(newItemPosition).getClass()) {
            return false;
        }
        if (mOldList.get(oldItemPosition) instanceof AllPlate.ResultBean.GroupsBean) {
            AllPlate.ResultBean.GroupsBean oldBean = (AllPlate.ResultBean.GroupsBean) mOldList.get(oldItemPosition);
            AllPlate.ResultBean.GroupsBean newBean = (AllPlate.ResultBean.GroupsBean) mNewList.get(newItemPosition);
            return oldBean.getId() == newBean.getId();
        }
        if (mOldList.get(oldItemPosition) instanceof Plate) {
            Plate oldBean = (Plate) mOldList.get(oldItemPosition);
            Plate newBean = (Plate) mNewList.get(newItemPosition);
            return oldBean.getId() == newBean.getId();
        }
        return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
//        if (mOldList.get(oldItemPosition).getClass() != mNewList.get(newItemPosition).getClass()) {
//            return false;
//        }
        if (mOldList.get(oldItemPosition) instanceof AllPlate.ResultBean.GroupsBean) {
            AllPlate.ResultBean.GroupsBean oldBean = (AllPlate.ResultBean.GroupsBean) mOldList.get(oldItemPosition);
            AllPlate.ResultBean.GroupsBean newBean = (AllPlate.ResultBean.GroupsBean) mNewList.get(newItemPosition);
            return TextUtils.equals(oldBean.getName(), newBean.getName());
        }
        if (mOldList.get(oldItemPosition) instanceof Plate) {
            Plate oldBean = (Plate) mOldList.get(oldItemPosition);
            Plate newBean = (Plate) mNewList.get(newItemPosition);
            return TextUtils.equals(oldBean.getName(), newBean.getName());
        }
        return false;
    }
}
