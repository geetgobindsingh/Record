package com.geet.utilility.record;

import android.app.Application;
import android.content.Context;

/**
 * Created by geetgobindsingh on 16/06/17.
 */

public class RecordApp extends Application {
    private static Context sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return sApplicationContext;
    }
}
