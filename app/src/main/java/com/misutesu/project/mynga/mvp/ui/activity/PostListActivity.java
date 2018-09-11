package com.misutesu.project.mynga.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.misutesu.project.lib_base.base.BaseActivity;
import com.misutesu.project.lib_base.base.recycler.RecyclerViewOnScrollListener;
import com.misutesu.project.lib_base.utils.BaseUtils;
import com.misutesu.project.lib_base.utils.EventBusUtils;
import com.misutesu.project.lib_base.utils.SnackBarUtils;
import com.misutesu.project.lib_base.utils.ThemeUtils;
import com.misutesu.project.mynga.R;
import com.misutesu.project.mynga.data.PostList;
import com.misutesu.project.mynga.entity.Plate;
import com.misutesu.project.mynga.entity.Post;
import com.misutesu.project.mynga.entity.PostListTitle;
import com.misutesu.project.mynga.event.PlateCollectEvent;
import com.misutesu.project.mynga.mvp.contract.PostListContract;
import com.misutesu.project.mynga.mvp.presenter.PostListPresenterImpl;
import com.misutesu.project.mynga.mvp.ui.adapter.PostAdapter;
import com.misutesu.project.mynga.router.DiscussRouter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

@Route(group = DiscussRouter.group, path = DiscussRouter.post_list)
public class PostListActivity extends BaseActivity<PostListContract.Presenter> implements PostListContract.View, SwipeRefreshLayout.OnRefreshListener
        , RecyclerViewOnScrollListener.OnScrollBottomListener, PostAdapter.OnPostClickListener {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_retry)
    MaterialButton btnRetry;

    private Plate mPlate;

    private int page = 1;
    private boolean isLoad;
    private boolean hasMore = true;
    private boolean loadMoreEnable = true;

    private boolean mIsStar;

    private PostAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

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
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolBar.setNavigationOnClickListener(v -> finish());

        refreshLayout.setColorSchemeColors(ThemeUtils.getColor(this, R.attr.colorPrimary));
        refreshLayout.setOnRefreshListener(this);

        mAdapter = new PostAdapter();
        mAdapter.setOnPostClickListener(this);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.addOnScrollListener(new RecyclerViewOnScrollListener(this));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        mPresenter.getCollectPlate(mPlate.getId());

        refreshLayout.post(() -> {
            refreshLayout.setRefreshing(true);
            onRefresh();
        });
    }

    @Override
    public void onRefresh() {
        isLoad = true;
        mPresenter.getPostList(true, mPlate.getId(), page);
    }

    @Override
    public void onScrollBottom() {
        if (!isLoad && hasMore && loadMoreEnable) {
            isLoad = true;
            mPresenter.getPostList(false, mPlate.getId(), page + 1);
        }
    }

    @Override
    public void getPlate(Plate plate) {
        mIsStar = plate != null;
        invalidateOptionsMenu();
    }

    @Override
    public void collectActionSuccess(boolean isStar) {
        mIsStar = isStar;
        invalidateOptionsMenu();
        EventBusUtils.post(new PlateCollectEvent());
    }

    @Override
    public void collectActionError() {
        SnackBarUtils.show(R.string.collect_action_error);
    }

    @Override
    public void getPostListSuccess(boolean isRefresh, PostList postList) {
        if (isRefresh) {
            page = 1;
            List list = new ArrayList();

            PostListTitle title = new PostListTitle();
            title.setPlateHeader(postList.getResult().getHeader());
            title.setChildPlates(postList.getResult().getSubForum());

            list.add(title);
            list.addAll(postList.getResult().getData());
            mAdapter.setData(list);
        } else {
            mAdapter.addData(postList.getResult().getData());
            page++;
        }
    }

    @Override
    public void getPostListError(boolean isRefresh, String msg) {
        if (isRefresh) {

        } else {
            mAdapter.setLoadMoreStatus(1);
            loadMoreEnable = false;
        }
        SnackBarUtils.show(msg);
    }

    @Override
    public void getPostListEnd() {
        refreshLayout.setRefreshing(false);
        isLoad = false;
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

    @OnClick({R.id.btn_retry})
    public void onViewClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_retry:
                btnRetry.setVisibility(View.INVISIBLE);
                refreshLayout.setRefreshing(true);
                onRefresh();
                break;
        }
    }

    @Override
    public void onMenuClick(PostListTitle title, int position) {
        switch (position) {
            case PostListTitle.TOP:
                break;
            case PostListTitle.HOT:
                break;
            case PostListTitle.ESSENCE:
                break;
            case PostListTitle.HEADER:
                break;
            case PostListTitle.CHILD:
                break;
        }
        Timber.d("TAG : %d", position);
    }

    @Override
    public void onPostClick(Post post) {
        Timber.d("TAG : %s", post.toString());
    }

    @Override
    public void onRetryLoadMoreClick() {
        if (!isLoad) {
            mAdapter.setLoadMoreStatus(0);
            loadMoreEnable = true;
            onScrollBottom();
        }
    }
}
