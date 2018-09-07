package com.misutesu.project.lib_base.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

import com.misutesu.project.lib_base.utils.UiUtils;

public class CompatToolbar extends Toolbar {

    int statusBarHeight;

    public CompatToolbar(Context context) {
        super(context);
        setup();
    }

    public CompatToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public CompatToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    private void setup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            statusBarHeight = UiUtils.getStatusBarHeight();
            if (statusBarHeight != 0) {
                setPadding(getPaddingLeft(), getPaddingTop() + statusBarHeight, getPaddingRight(), getPaddingBottom());
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, UiUtils.dip2px(56) + statusBarHeight);
    }
}
