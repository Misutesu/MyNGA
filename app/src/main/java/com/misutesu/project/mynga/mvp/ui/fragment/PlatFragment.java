package com.misutesu.project.mynga.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.util.FixedPreloadSizeProvider;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.misutesu.project.lib_base.base.BaseFragment;
import com.misutesu.project.lib_base.base.BasePresenter;
import com.misutesu.project.lib_base.http.imageloader.GlideNGA;
import com.misutesu.project.lib_base.utils.StringUtils;
import com.misutesu.project.lib_base.utils.UiUtils;
import com.misutesu.project.mynga.R;
import com.misutesu.project.mynga.config.ServerConfig;
import com.misutesu.project.mynga.data.AllPlate;
import com.misutesu.project.mynga.entity.Plate;
import com.misutesu.project.mynga.mvp.contract.PlateContract;
import com.misutesu.project.mynga.mvp.presenter.PlatePresenterImpl;
import com.misutesu.project.mynga.mvp.ui.adapter.PlateAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

public class PlatFragment extends BaseFragment<PlateContract.Presenter> implements PlateContract.View {

    private static final String KEY_RESULT_BEAN = "key_result_bean";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private PlateAdapter mAdapter;

    private AllPlate.ResultBean mResultBean;

    public static PlatFragment getInstance(AllPlate.ResultBean resultBean) {
        PlatFragment fragment = new PlatFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        bundle.putSerializable(KEY_RESULT_BEAN, resultBean);
        return fragment;
    }

    @Override
    protected PlateContract.Presenter bindPresenter() {
        return new PlatePresenterImpl(this);
    }

    @Override
    protected int bindXML() {
        return R.layout.fragment_plate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            mResultBean = (AllPlate.ResultBean) getArguments().getSerializable(KEY_RESULT_BEAN);
        }

        if (mResultBean == null) {
            mPresenter.getCollectPlats();
        } else {
            List<Plate> list = new ArrayList<>();
            for (AllPlate.ResultBean.GroupsBean bean : mResultBean.getGroups()) {
                list.addAll(bean.getForums());
            }
            initRecycler(list);
        }
    }

    @Override
    public void getCollectPlatsSuccess(List<Plate> plates) {
        initRecycler(plates);
    }

    @Override
    public void getCollectPlatsError() {

    }

    private void initRecycler(List<Plate> plates) {
        mAdapter = new PlateAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setData(plates);
    }
}
