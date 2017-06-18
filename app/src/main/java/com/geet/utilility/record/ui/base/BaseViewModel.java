package com.geet.utilility.record.ui.base;

import android.arch.lifecycle.ViewModel;

/**
 * Created by geetgobindsingh on 16/06/17.
 */

public abstract class BaseViewModel<V extends IBaseView> extends ViewModel implements IBaseViewModel<V> {
    private static final String TAG = "BaseViewModel";
    private V iView;

    @Override
    public void attachView(V view) {
        this.iView = view;
    }

    @Override
    public V getView() {
        return iView;
    }
}
