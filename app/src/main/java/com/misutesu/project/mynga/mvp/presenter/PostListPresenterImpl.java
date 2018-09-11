package com.misutesu.project.mynga.mvp.presenter;

import com.misutesu.project.lib_base.base.BasePresenter;
import com.misutesu.project.mynga.data.PostList;
import com.misutesu.project.mynga.entity.Plate;
import com.misutesu.project.mynga.mvp.contract.PostListContract;
import com.misutesu.project.mynga.mvp.model.PostListModelImpl;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(plate -> mRootView.getPlate(plate)
                        , throwable -> mRootView.getPlate(null)));
    }

    @Override
    public void insertPlate(Plate plate) {
        addDisposable(Completable.fromAction(() -> mModel.insertPlate(plate))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> mRootView.collectActionSuccess(true)
                        , throwable -> mRootView.collectActionError()));
    }

    @Override
    public void deletePlate(Plate plate) {
        addDisposable(Completable.fromAction(() -> mModel.deletePlate(plate))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> mRootView.collectActionSuccess(false)
                        , throwable -> mRootView.collectActionError()));
    }

    @Override
    public void getPostList(boolean isRefresh, int plateId, int page) {
        addDisposable(mModel.getPostList(plateId, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                            if (list.getCode() == 0) {
                                mRootView.getPostListSuccess(isRefresh, list);
                            } else {
                                mRootView.getPostListError(isRefresh, list.getMsg());
                            }
                        }
                        , throwable -> {
                            mRootView.getPostListError(isRefresh, throwable.getMessage());
                            mRootView.getPostListEnd();
                        }
                        , () -> mRootView.getPostListEnd()));
    }
}
