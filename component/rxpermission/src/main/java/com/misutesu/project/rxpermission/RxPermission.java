package com.misutesu.project.rxpermission;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import rx.subjects.BehaviorSubject;

public class RxPermission {
    private static RxPermission rxPermission;

    private static RxPermission getInstance() {
        if (rxPermission == null) {
            synchronized (RxPermission.class) {
                if (rxPermission == null) {
                    rxPermission = new RxPermission();
                }
            }
        }
        return rxPermission;
    }

    public static RxPermission addPermissions(List<String> permissions) {
        return getInstance().setPermissions(permissions);
    }

    public static RxPermission addPermissions(String[] permissions) {
        return addPermissions(Arrays.asList(permissions));
    }

    public static RxPermission addPermissions(String permission) {
        return addPermissions(Collections.singletonList(permission));
    }

    private List<String> mPermissions = new ArrayList<>();

    private RxPermission setPermissions(List<String> permissions) {
        mPermissions.clear();
        mPermissions.addAll(permissions);
        return this;
    }

    public BehaviorSubject<Boolean> request(FragmentActivity activity) {
        return request(activity.getSupportFragmentManager());
    }

    public BehaviorSubject<Boolean> request(Fragment fragment) {
        return request(fragment.getActivity());
    }

    private BehaviorSubject<Boolean> request(FragmentManager fragmentManager) {
        PermissionFragment fragment = (PermissionFragment) fragmentManager.findFragmentByTag(PermissionFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = PermissionFragment.getInstance();
            fragmentManager.beginTransaction().add(fragment, fragment.getClass().getSimpleName()).commit();
        } else if (fragment.isDetached()) {
            fragmentManager.beginTransaction().attach(fragment).commit();
        }
        return getPermissions(fragment);
    }

    private BehaviorSubject<Boolean> getPermissions(PermissionFragment fragment) {
        return fragment.setPermissions(mPermissions);
    }
}
