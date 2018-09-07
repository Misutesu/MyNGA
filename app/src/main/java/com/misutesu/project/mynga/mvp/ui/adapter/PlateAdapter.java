package com.misutesu.project.mynga.mvp.ui.adapter;

import android.view.View;

import com.misutesu.project.lib_base.base.recycler.BaseAdapter;
import com.misutesu.project.lib_base.base.recycler.BaseHolder;
import com.misutesu.project.mynga.R;
import com.misutesu.project.mynga.entity.Plate;
import com.misutesu.project.mynga.mvp.ui.holder.PlateHolder;

public class PlateAdapter extends BaseAdapter<Plate> {

    @Override
    protected int bindXML(int viewType) {
        return R.layout.item_plate;
    }

    @Override
    protected BaseHolder<Plate> getHolder(View view, int viewType) {
        return new PlateHolder(view);
    }
}
