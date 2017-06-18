package com.geet.utilility.record.ui.base;

/**
 * Created by Geet on 23-05-2017.
 */

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * onAttach() and onDetach(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getView() and checking if view attached by
 * calling isViewAttached().
 */
public abstract class BasePresenter<V extends IView> implements IPresenter<V>, LifecycleObserver {

    private static final String TAG = "BasePresenter";
    private V iView;

    //region IPresenter implemented methods
    @Override
    public void onAttach(V iView) {
        this.iView = iView;
        if (iView instanceof LifecycleOwner) {
            ((LifecycleOwner) iView).getLifecycle().addObserver(this);
        }
    }

    @Override
    public void onDetach() {
        this.iView = null;
    }

    @Override
    public boolean isViewAttached() {
        return (iView != null);
    }

    @Override
    public V getView() {
        return iView;
    }
    //endregion
}
