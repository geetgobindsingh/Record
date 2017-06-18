package com.geet.utilility.record.ui.addrecord.viewmodel;

import android.os.Message;
import android.support.annotation.Nullable;

import com.geet.utilility.record.R;
import com.geet.utilility.record.core.DatabaseHandler;
import com.geet.utilility.record.core.DatabaseHandlerThread;
import com.geet.utilility.record.data.model.Record;
import com.geet.utilility.record.ui.addrecord.view.AddRecordActivity;
import com.geet.utilility.record.ui.addrecord.view.IAddRecordView;
import com.geet.utilility.record.ui.base.BaseActivity;
import com.geet.utilility.record.ui.base.BaseViewModel;
import com.geet.utilility.record.ui.base.IBaseView;

import java.util.Date;
import java.util.UUID;

/**
 * Created by geetgobindsingh on 17/06/17.
 */

public class AddRecordViewModel<V extends IAddRecordView> extends BaseViewModel<V> {

    @Nullable
    private Long startDate;
    private DatabaseHandlerThread mDatabaseHandlerThread;
    private AddRecordActivity mAddRecordActivity;

    public AddRecordViewModel() {
        mDatabaseHandlerThread = new DatabaseHandlerThread(AddRecordViewModel.class.getSimpleName());
        mDatabaseHandlerThread.start();
    }

    @Nullable
    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(final long startDate) {
        this.startDate = startDate;
    }

    public void saveRecord(final String whatEditText, final String whyEditText, final String totalTimeTaken) {

        Record record = new Record();
        record.setId(UUID.randomUUID().toString());
        record.setWhat(whatEditText);
        record.setWhy(whyEditText);
        record.setWhen(new Date());
        record.setRecordTime(totalTimeTaken);

        mDatabaseHandlerThread.saveRecordAsync(record, new DatabaseHandler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MSG_SUCCESS: {
                        getView().recordSavedSuccessfully();
                        break;
                    }
                    case MSG_ERROR: {
                            getView().recordSavingError(((Exception) msg.obj).getMessage());
                        }
                        break;
                    }
                }
            });
    }
}
