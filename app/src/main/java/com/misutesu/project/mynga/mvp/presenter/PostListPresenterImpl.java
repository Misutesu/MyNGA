package com.misutesu.project.mynga.mvp.presenter;

import com.misutesu.project.lib_base.base.BasePresenter;
import com.misutesu.project.mynga.data.PostList;
import com.misutesu.project.mynga.entity.Plate;
import com.misutesu.project.mynga.mvp.contract.PostListContract;
import com.misutesu.project.mynga.mvp.model.PostListModelImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PostListPresenterImpl extends BasePresenter<PostListContract.Model, PostListContract.View> implements PostListContract.Presenter {
    public PostListPresenterImpl(PostListContract.View view) {
        super(view);
    }

    @Override
    protected PostListContract.Model bindModel() {
        return new PostListModelImpl();
    }

    @Override
    public void getCollectPlate(int id) {
        addDisposable(mModel.getCollectPlate(id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> mRootView.getPlate(null))
                .subscribe(plate -> mRootView.getPlate(plate)));
    }

    @Override
    public void insertPlate(Plate plate) {
        mModel.insertPlate(plate);
    }

    @Override
    public void deletePlate(Plate plate) {
        mModel.deletePlate(plate);
    }

    @Override
    public void getPostList(boolean isRefresh, int plateId, int page) {
        addDisposable(mModel.getPostList(plateId, page)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> mRootView.getPostListError(throwable.getMessage()))
                .doOnComplete(() -> mRootView.getPostListEnd())
                .subscribe(list -> {
                    if (list.getCode() == 0) {
                        mRootView.getPostListSuccess(isRefresh, list);
                    } else {
                        mRootView.getPostListError(list.getMsg());
                    }
                }));
    }
}
