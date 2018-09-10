package com.misutesu.project.mynga.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v4.view.GravityCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.misutesu.project.lib_base.base.BaseFragment;
import com.misutesu.project.lib_base.base.recycler.BaseAdapter;
import com.misutesu.project.lib_base.utils.SnackBarUtils;
import com.misutesu.project.mynga.R;
import com.misutesu.project.mynga.data.AllPlate;
import com.misutesu.project.mynga.entity.Plate;
import com.misutesu.project.mynga.event.PlateCollectEvent;
import com.misutesu.project.mynga.mvp.contract.PlateContract;
import com.misutesu.project.mynga.mvp.presenter.PlatePresenterImpl;
import com.misutesu.project.mynga.mvp.ui.activity.MainActivity;
import com.misutesu.project.mynga.mvp.ui.adapter.PlateAdapter;
import com.misutesu.project.mynga.mvp.ui.diffcallback.PlateCallBack;
import com.misutesu.project.mynga.mvp.ui.itemhelper.PlateItemHelper;
import com.misutesu.project.mynga.router.DiscussRouter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.b.V;
import timber.log.Timber;

public class PlatFragment extends BaseFragment<PlateContract.Presenter> implements PlateContract.View
        , PlateAdapter.OnPlateItemClickListener, BaseAdapter.OnItemMoveListener {

    private static final String KEY_RESULT_BEAN = "key_result_bean";

    private final int RECYCLER_COUNT = 3;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.to_collect_btn)
    MaterialButton toCollectBtn;

    private PlateAdapter mAdapter;
    private GridLayoutManager mLayoutManager;
    private PlateItemHelper mItemHelper;

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
    protected boolean useEventBus() {
        return true;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            mResultBean = (AllPlate.ResultBean) getArguments().getSerializable(KEY_RESULT_BEAN);
        }

        mAdapter = new PlateAdapter();
        mLayoutManager = new GridLayoutManager(getContext(), RECYCLER_COUNT);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (mAdapter.getItem(position) instanceof Plate) {
                    return 1;
                }
                return RECYCLER_COUNT;
            }
        });
        recyclerView.setLayoutManager(mLayoutManager);
        if (mResultBean == null) {
            mItemHelper = new PlateItemHelper(mAdapter);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mItemHelper);
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
        List list = new ArrayList();
        list.add(new AllPlate.ResultBean.GroupsBean(getString(R.string.collect_move_hint)));
        list.addAll(plates);
        initRecycler(list);
    }

    @Override
    public void getCollectPlatsError() {
        SnackBarUtils.show(R.string.get_collect_plate_error);
    }

    private void initRecycler(List plates) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new PlateCallBack(mAdapter.getData(), plates), true);
        diffResult.dispatchUpdatesTo(mAdapter);
        mAdapter.replaceData(plates);
        if (mResultBean == null) {
            toCollectBtn.setVisibility(plates.size() == 1 ? View.VISIBLE : View.INVISIBLE);
        }
    }

    @Override
    public void onItemClick(int position, Plate plate) {
        if (plate.isIs_forumlist()) {

        } else {
            ARouter.getInstance()
                    .build(DiscussRouter.post_list)
                    .withSerializable(Plate.class.getName(), plate)
                    .navigation();
        }
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (mAdapter.getItem(fromPosition) instanceof Plate && mAdapter.getItem(toPosition) instanceof Plate) {
            Plate fromPlate = (Plate) mAdapter.getItem(fromPosition);
            Plate toPlate = (Plate) mAdapter.getItem(toPosition);
            mPresenter.exchangePlate(fromPlate, toPlate);
        }
    }

    @OnClick({R.id.to_collect_btn})
    public void onViewClicked(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.to_collect_btn:
                try {
                    ((MainActivity) getActivity()).toCollect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void plateCollect(PlateCollectEvent event) {
        if (mResultBean == null) {
            mPresenter.getCollectPlats();
        }
    }
}
