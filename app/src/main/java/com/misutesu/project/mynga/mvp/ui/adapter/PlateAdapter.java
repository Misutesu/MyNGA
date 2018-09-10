package com.misutesu.project.mynga.mvp.ui.adapter;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.misutesu.project.lib_base.base.recycler.BaseAdapter;
import com.misutesu.project.lib_base.base.recycler.BaseHolder;
import com.misutesu.project.lib_base.http.imageloader.GlideNGA;
import com.misutesu.project.lib_base.utils.StringUtils;
import com.misutesu.project.lib_base.utils.ViewUtils;
import com.misutesu.project.mynga.R;
import com.misutesu.project.mynga.config.ServerConfig;
import com.misutesu.project.mynga.data.AllPlate;
import com.misutesu.project.mynga.entity.Plate;

import java.util.Collections;

import butterknife.BindView;

public class PlateAdapter extends BaseAdapter {

    private final int PLATE_TYPE = R.layout.item_plate_type;
    private final int PLATE = R.layout.item_plate;

    public interface OnPlateItemClickListener {
        void onItemClick(int position, Plate plate);
    }

    private OnPlateItemClickListener onPlateItemClickListener;

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position) instanceof Plate) {
            return PLATE;
        }
        return PLATE_TYPE;
    }

    @Override
    protected int bindXML(int viewType) {
        return viewType;
    }

    @Override
    protected BaseHolder getHolder(View view, int viewType) {
        if (viewType == PLATE) {
            return new PlateHolder(view);
        }
        return new PlateTypeHolder(view);
    }

    public void setOnPlateItemClickListener(OnPlateItemClickListener onPlateItemClickListener) {
        this.onPlateItemClickListener = onPlateItemClickListener;
    }

    public class PlateTypeHolder extends BaseHolder<AllPlate.ResultBean.GroupsBean> {

        @BindView(R.id.tv_type)
        AppCompatTextView tvType;

        PlateTypeHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void setData(AllPlate.ResultBean.GroupsBean data, int position) {
            tvType.setText(data.getName());
        }
    }

    public class PlateHolder extends BaseHolder<Plate> implements View.OnClickListener {

        @BindView(R.id.ll_item)
        LinearLayout llItem;
        @BindView(R.id.iv_icon)
        AppCompatImageView ivIcon;
        @BindView(R.id.tv_name)
        AppCompatTextView tvName;

        PlateHolder(@NonNull View itemView) {
            super(itemView);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ViewUtils.setBorderlessBg(ivIcon);
            } else {
                ViewUtils.setBorderlessBg(llItem);
            }
        }

        @Override
        public void setData(Plate data, int position) {
            llItem.setTag(data);
            llItem.setOnClickListener(this);
            tvName.setText(data.getName());
            GlideNGA.with(mContext)
                    .load(StringUtils.format(ServerConfig.getUrl(ServerConfig.SERVER_ICON), String.valueOf(data.getId())))
                    .placeholder(R.drawable.default_plate)
                    .error(R.drawable.default_plate)
                    .into(ivIcon);
        }

        @Override
        public void onClick(View v) {
            if (onPlateItemClickListener != null) {
                onPlateItemClickListener.onItemClick(getAdapterPosition(), (Plate) v.getTag());
            }
        }
    }
}
