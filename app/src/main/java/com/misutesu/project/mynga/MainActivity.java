package com.misutesu.project.mynga;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.misutesu.project.lib_base.base.BaseActivity;
import com.misutesu.project.lib_base.base.BasePresenter;
import com.misutesu.project.lib_base.mvp.IPresenter;
import com.misutesu.project.mynga.entity.Server;

import java.util.List;
import java.util.Timer;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import timber.log.Timber;

public class MainActivity extends BaseActivity<BasePresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BmobQuery<Server> query = new BmobQuery<>();
        query.findObjects(new FindListener<Server>() {
            @Override
            public void done(List<Server> list, BmobException e) {
                if (e == null) {
                    Timber.d("TAG" + list.toString());
                } else {
                    Timber.d("TAG" + e.toString());
                }
            }
        });
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }
}
