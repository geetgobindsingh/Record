package com.geet.utilility.record.ui.recordlist.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Message;

import com.geet.utilility.record.core.DatabaseHandler;
import com.geet.utilility.record.core.DatabaseHandlerThread;
import com.geet.utilility.record.data.model.Record;
import com.geet.utilility.record.ui.base.BasePresenter;
import com.geet.utilility.record.ui.recordlist.view.IRecordListView;
import com.geet.utilility.record.ui.recordlist.view.RecordListActivity;

import java.util.ArrayList;

/**
 * Created by geetgobindsingh on 16/06/17.
 */

public class RecordListPresenter<V extends IRecordListView> extends BasePresenter<V> {

    private boolean mFirstLoad = true;

    private final MutableLiveData<ArrayList<Record>> recordList = new MutableLiveData<>();
    ;

    public RecordListPresenter(V recordListView) {
        onAttach(recordListView);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        loadRecordList(true);
    }

    public void loadRecordList(boolean forceUpdate) {
        if (forceUpdate || mFirstLoad) {
            loadALllRecords();
            mFirstLoad = false;
        }
    }

    public MutableLiveData<ArrayList<Record>> getAllRecords() {
        return recordList;
    }

    private MutableLiveData<ArrayList<Record>> loadALllRecords() {
        getView().showProgressBar(true);
        DatabaseHandlerThread.getInstance()
                .getAllRecordsAsync(new DatabaseHandler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what) {
                            case MSG_SUCCESS: {
                                recordList.setValue((ArrayList<Record>) msg.obj);
                                getView().showProgressBar(false);
                                break;
                            }
                            case MSG_ERROR: {
                                ((Exception)msg.obj).printStackTrace();
                                getView().showErrorMessage();
                                getView().showProgressBar(false);
                                break;
                            }
                        }
                    }
                });
        return recordList;
    }

}
