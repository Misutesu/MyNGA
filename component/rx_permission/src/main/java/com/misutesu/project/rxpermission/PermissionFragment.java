package com.misutesu.project.rxpermission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import rx.subjects.BehaviorSubject;

public class PermissionFragment extends Fragment {

    private final int PERMISSION_REQUEST_CODE = 100;

    public static PermissionFragment getInstance() {
        return new PermissionFragment();
    }

    private BehaviorSubject<Boolean> behaviorSubject = BehaviorSubject.create();

    private List<String> mPermissions = new ArrayList<>();


    public BehaviorSubject<Boolean> setPermissions(List<String> permissions) {
        mPermissions.clear();
        mPermissions.addAll(permissions);
        return behaviorSubject;
    }

    private void getPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            behaviorSubject.onNext(true);
        } else {
            List<String> permissions = new ArrayList<>();
            for (String p : mPermissions) {
                if (ContextCompat.checkSelfPermission(getActivity(), p) != PackageManager.PERMISSION_GRANTED) {
                    permissions.add(p);
                }
            }
            if (!permissions.isEmpty()) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), PERMISSION_REQUEST_CODE);
            } else {
                behaviorSubject.onNext(true);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getPermissions();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != PERMISSION_REQUEST_CODE) {
            return;
        }
        boolean result = true;
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                result = false;
                break;
            }
        }
        behaviorSubject.onNext(result);
    }
}
