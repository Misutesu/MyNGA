package com.misutesu.project.mynga.mvp.ui.holder;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.misutesu.project.lib_base.base.recycler.BaseHolder;
import com.misutesu.project.lib_base.http.imageloader.GlideNGA;
import com.misutesu.project.lib_base.utils.StringUtils;
import com.misutesu.project.lib_base.utils.UiUtils;
import com.misutesu.project.mynga.R;
import com.misutesu.project.mynga.config.ServerConfig;
import com.misutesu.project.mynga.entity.Plate;

import butterknife.BindView;

public class PlateHolder extends BaseHolder<Plate> {

    @BindView(R.id.iv_icon)
    AppCompatImageView ivIcon;

    public PlateHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void setData(Plate data, int position) {
        GlideNGA.with(mContext)
                .load(StringUtils.format(ServerConfig.getUrl(ServerConfig.SERVER_ICON), String.valueOf(data.getId())))
                .override(UiUtils.dip2px(50), UiUtils.dip2px(50))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        return false;
                    }
                })
                .into(ivIcon);
    }
}
