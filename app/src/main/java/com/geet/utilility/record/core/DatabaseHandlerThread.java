package com.geet.utilility.record.core;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.geet.utilility.record.data.DataBaseManager;
import com.geet.utilility.record.data.model.Record;

import java.lang.ref.WeakReference;

/**
 * Created by geetgobindsingh on 18/06/17.
 */

public class DatabaseHandlerThread extends HandlerThread {

    Handler handler;

    public DatabaseHandlerThread(String name) {
        super(name);
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        handler = new Handler(getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
    }

    public void saveRecordAsync(final Record record, final DatabaseHandler databaseHandler) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    DataBaseManager.getInstance().saveRecord(record);//this is already a sync action
                    databaseHandler.sendMessage(databaseHandler.obtainMessage(DatabaseHandler.MSG_SUCCESS));
                } catch (Exception ex) {
                    databaseHandler.sendMessage(databaseHandler.obtainMessage(DatabaseHandler.MSG_ERROR, ex));
                }
            }
        });
    }
}
