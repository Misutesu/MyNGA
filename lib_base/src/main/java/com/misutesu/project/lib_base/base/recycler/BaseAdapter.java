package com.misutesu.project.lib_base.base.recycler;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseHolder<T>> {

    public interface OnItemMoveListener {
        void onItemMove(int fromPosition, int toPosition);
    }

    private OnItemMoveListener onItemMoveListener;

    protected List<T> mList = new ArrayList<>();

    protected abstract BaseHolder<T> getHolder(ViewGroup viewGroup, int position);

    protected View getView(ViewGroup viewGroup, @LayoutRes int res) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(res, viewGroup, false);
    }

    @NonNull
    @Override
    public BaseHolder<T> onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return getHolder(viewGroup, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder<T> viewHolder, int position) {
        viewHolder.setData(mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnItemMoveListener(OnItemMoveListener onItemMoveListener) {
        this.onItemMoveListener = onItemMoveListener;
    }

    public List<T> getData() {
        return mList;
    }

    public T getItem(int position) {
        return mList.get(position);
    }

    public void replaceData(List<T> list) {
        if (list != null && !list.isEmpty()) {
            mList.clear();
            mList.addAll(list);
        }
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

    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        if (onItemMoveListener != null) {
            onItemMoveListener.onItemMove(fromPosition, toPosition);
        }
    }
}
