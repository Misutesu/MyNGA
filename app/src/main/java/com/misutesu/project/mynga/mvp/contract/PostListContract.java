package com.misutesu.project.mynga.mvp.contract;

import com.misutesu.project.lib_base.mvp.IModel;
import com.misutesu.project.lib_base.mvp.IPresenter;
import com.misutesu.project.lib_base.mvp.IView;
import com.misutesu.project.mynga.data.PostList;
import com.misutesu.project.mynga.entity.Plate;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface PostListContract {
    interface Model extends IModel {

        Single<Plate> getCollectPlate(int id);

        void insertPlate(Plate plate);

        void deletePlate(Plate plate);

        Observable<PostList> getPostList(int plateId, int page);
    }

    interface View extends IView {
        void getPlate(Plate plate);

        void getPostListSuccess(boolean isRefresh, PostList list);

        void getPostListError(String msg);

        void getPostListEnd();
    }

    interface Presenter extends IPresenter {

        void getCollectPlate(int id);

        void insertPlate(Plate plate);

        void deletePlate(Plate plated);

        void getPostList(boolean isRefresh, int plateId, int page);
    }
}
