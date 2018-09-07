package com.misutesu.project.mynga.mvp.ui.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

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
                .into(ivIcon);
    }
}
