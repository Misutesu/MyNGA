package com.misutesu.project.lib_base.base.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseHolder<T>> {

    protected List<T> mList = new ArrayList<>();

    protected abstract int bindXML(int viewType);

    protected abstract BaseHolder<T> getHolder(View view, int viewType);

    @NonNull
    @Override
    public BaseHolder<T> onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return getHolder(LayoutInflater.from(viewGroup.getContext()).inflate(bindXML(viewType), viewGroup, false), viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder<T> viewHolder, int position) {
        viewHolder.setData(mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<T> getData() {
        return mList;
    }

    public T getItem(int position) {
        return mList.get(position);
    }

    public void setData(List<T> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        int oldSize = mList.size();
        if (oldSize != 0) {
            mList.clear();
            notifyItemRangeRemoved(0, oldSize);
        }
        mList.addAll(list);
        notifyItemRangeInserted(0, mList.size());
    }

    public void addData(List<T> list) {
        if (list != null) {
            mList.addAll(list);
            notifyItemRangeInserted(mList.size(), mList.size());
        }
    }

    public void addData(T data, int position) {
        if (position == -1) {
            mList.add(data);
            notifyItemInserted(mList.size() - 1);
        } else {
            mList.add(position, data);
            notifyItemInserted(position);
        }
    }

    public void removeData(int position) {
        if (position > mList.size()) {
            return;
        }
        notifyItemRemoved(position);
        mList.remove(position);
    }

    public void removeAll() {
        notifyItemRangeRemoved(0, mList.size());
        mList.clear();
    }
}
