package com.geet.utilility.record.ui.base;

/**
 * Created by Geet on 23-05-2017.
 */


/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 */

public interface IPresenter<V extends IView>{

    void onAttach(V iView);

    void onDetach();

    V getView();

    boolean isViewAttached();
}
