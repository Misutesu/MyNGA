package com.misutesu.project.mynga.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.util.FixedPreloadSizeProvider;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.misutesu.project.lib_base.base.BaseFragment;
import com.misutesu.project.lib_base.base.BasePresenter;
import com.misutesu.project.lib_base.base.recycler.BaseAdapter;
import com.misutesu.project.lib_base.http.imageloader.GlideNGA;
import com.misutesu.project.lib_base.utils.SnackBarUtils;
import com.misutesu.project.lib_base.utils.StringUtils;
import com.misutesu.project.lib_base.utils.UiUtils;
import com.misutesu.project.mynga.R;
import com.misutesu.project.mynga.config.ServerConfig;
import com.misutesu.project.mynga.data.AllPlate;
import com.misutesu.project.mynga.entity.Plate;
import com.misutesu.project.mynga.mvp.contract.PlateContract;
import com.misutesu.project.mynga.mvp.presenter.PlatePresenterImpl;
import com.misutesu.project.mynga.mvp.ui.adapter.PlateAdapter;
import com.misutesu.project.mynga.mvp.ui.diffcallback.PlateCallBack;
import com.misutesu.project.mynga.mvp.ui.itemhelper.PlateItemHelper;
import com.misutesu.project.mynga.router.DiscussRouter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import timber.log.Timber;

public class PlatFragment extends BaseFragment<PlateContract.Presenter> implements PlateContract.View
        , PlateAdapter.OnPlateItemClickListener, BaseAdapter.OnItemMoveListener {

    private static final String KEY_RESULT_BEAN = "key_result_bean";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private PlateAdapter mAdapter;
    private GridLayoutManager mLayoutManager;

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
        mAdapter = new PlateAdapter();
        mLayoutManager = new GridLayoutManager(getContext(), 3);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (mAdapter.getItem(position) instanceof Plate) {
                    return 1;
                }
                return 3;
            }
        });
        recyclerView.setLayoutManager(mLayoutManager);
        if (mResultBean == null) {
            ItemTouchHelper.Callback callback = new PlateItemHelper(mAdapter);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
            itemTouchHelper.attachToRecyclerView(recyclerView);
        }
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnPlateItemClickListener(this);
        mAdapter.setOnItemMoveListener(this);

        if (mResultBean == null) {
            mPresenter.getCollectPlats();
        } else {
            List list = new ArrayList<>();
            for (AllPlate.ResultBean.GroupsBean bean : mResultBean.getGroups()) {
                list.add(bean);
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
        SnackBarUtils.show(R.string.get_collect_plate_error);
    }

    private void initRecycler(List plates) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new PlateCallBack(mAdapter.getData(), plates), true);
        diffResult.dispatchUpdatesTo(mAdapter);
        mAdapter.replaceData(plates);
    }

    @Override
    public void onItemClick(int position, Plate plate) {
        ARouter.getInstance()
                .build(DiscussRouter.post_list)
                .withSerializable(Plate.class.getName(), plate)
                .navigation();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (mAdapter.getItem(fromPosition) instanceof Plate && mAdapter.getItem(toPosition) instanceof Plate) {
            Plate fromPlate = (Plate) mAdapter.getItem(fromPosition);
            Plate toPlate = (Plate) mAdapter.getItem(toPosition);
            mPresenter.exchangePlate(fromPlate, toPlate);
        }
    }
}
