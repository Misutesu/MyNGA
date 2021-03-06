package com.misutesu.project.lib_base.base.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by wujiaquan on 2017/4/11.
 */

public class RecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {

    public interface OnScrollBottomListener {
        void onScrollBottom();
    }

    public interface OnScrollListener {
        void onScrollY(int dy);
    }

    private int firstItem;
    private int lastItem;

    private OnScrollBottomListener mOnScrollBottomListener;
    private OnScrollListener mOnScrollListener;

    public RecyclerViewOnScrollListener(OnScrollBottomListener onScrollBottomListener) {
        mOnScrollBottomListener = onScrollBottomListener;
    }

    public RecyclerViewOnScrollListener(OnScrollListener onScrollListener) {
        mOnScrollListener = onScrollListener;
    }

    public RecyclerViewOnScrollListener(OnScrollBottomListener onScrollBottomListener, OnScrollListener onScrollListener) {
        mOnScrollBottomListener = onScrollBottomListener;
        mOnScrollListener = onScrollListener;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int totalItemCount = layoutManager != null ? layoutManager.getItemCount() : 0;
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            firstItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            lastItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
            if (lastItem != -1) {
                lastItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        }
        if (lastItem == totalItemCount - 1 && (dx > 0 || dy > 0)) {
            if (mOnScrollBottomListener != null) {
                mOnScrollBottomListener.onScrollBottom();
            }
        }

        if (mOnScrollListener != null) {
            mOnScrollListener.onScrollY(dy);
        }
    }
}
