package com.geet.utilility.record;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.geet.utilility.record.data.DataBaseManager;

/**
 * Created by geetgobindsingh on 16/06/17.
 */

public class RecordApp extends Application {
    private static Context sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationContext = getApplicationContext();
        DataBaseManager.getInstance();

        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }

    public static Context getAppContext() {
        return sApplicationContext;
    }
}
