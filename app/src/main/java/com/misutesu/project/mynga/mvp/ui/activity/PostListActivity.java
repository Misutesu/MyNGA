package com.misutesu.project.mynga.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.misutesu.project.lib_base.base.BaseActivity;
import com.misutesu.project.lib_base.utils.BaseUtils;
import com.misutesu.project.lib_base.utils.SnackBarUtils;
import com.misutesu.project.lib_base.utils.ThemeUtils;
import com.misutesu.project.mynga.R;
import com.misutesu.project.mynga.data.PostList;
import com.misutesu.project.mynga.entity.Plate;
import com.misutesu.project.mynga.mvp.contract.PostListContract;
import com.misutesu.project.mynga.mvp.presenter.PostListPresenterImpl;
import com.misutesu.project.mynga.router.DiscussRouter;

import java.util.Objects;

import butterknife.BindView;
import timber.log.Timber;

@Route(group = DiscussRouter.group, path = DiscussRouter.post_list)
public class PostListActivity extends BaseActivity<PostListContract.Presenter> implements PostListContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private Plate mPlate;

    private int page = 1;

    private boolean mIsStar;

    @Override
    protected PostListContract.Presenter bindPresenter() {
        return new PostListPresenterImpl(this);
    }

    @Override
    protected int bindXML() {
        return R.layout.activity_post_list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPlate = (Plate) getIntent().getSerializableExtra(Plate.class.getName());

        BaseUtils.addStatusBarHeightAndPadding(toolBar);

        toolBar.setTitle(mPlate.getName());
        setSupportActionBar(toolBar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolBar.setNavigationOnClickListener(v -> finish());

        refreshLayout.setColorSchemeColors(ThemeUtils.getColor(this, R.attr.colorPrimary));
        refreshLayout.setOnRefreshListener(this);

        mPresenter.getCollectPlate(mPlate.getId());

        mPresenter.getPostList(true, mPlate.getId(), page);
    }

    @Override
    public void onRefresh() {
        page = 1;
        mPresenter.getPostList(true, mPlate.getId(), page);
    }

    @Override
    public void getPlate(Plate plate) {
        mIsStar = plate == null;
        invalidateOptionsMenu();
    }

    @Override
    public void getPostListSuccess(boolean isRefresh, PostList list) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
    }

    @Override
    public void getPostListError(String msg) {
        SnackBarUtils.show(msg);
    }

    @Override
    public void getPostListEnd() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_list_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mIsStar) {
            menu.findItem(R.id.tool_bar_star).setVisible(true);
            menu.findItem(R.id.tool_bar_no_star).setVisible(false);
        } else {
            menu.findItem(R.id.tool_bar_star).setVisible(false);
            menu.findItem(R.id.tool_bar_no_star).setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.tool_bar_star) {
            mPresenter.deletePlate(mPlate);
            return true;
        } else if (id == R.id.tool_bar_no_star) {
            mPresenter.insertPlate(mPlate);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
