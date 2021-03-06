package com.misutesu.project.lib_base.base.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {

    protected Context mContext;

    public BaseHolder(@NonNull View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    public abstract void setData(T data, int position);

}
