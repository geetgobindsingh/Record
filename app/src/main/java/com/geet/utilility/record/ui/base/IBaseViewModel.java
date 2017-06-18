package com.geet.utilility.record.ui.base;

/**
 * Created by geetgobindsingh on 18/06/17.
 */

public interface IBaseViewModel<V extends IBaseView> {
    void attachView(V view);
    V getView();
}
