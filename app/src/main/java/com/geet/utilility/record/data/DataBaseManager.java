package com.geet.utilility.record.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;

import com.amitshekhar.DebugDB;
import com.geet.utilility.record.RecordApp;
import com.geet.utilility.record.data.model.Record;

import java.util.List;

/**
 * Created by geetgobindsingh on 16/06/17.
 */

public class DataBaseManager {

    private static DataBaseManager sInstance = null;
    private AppDatabase appDatabase, memoryDatabase;

    private DataBaseManager() {
    }

    public static DataBaseManager getInstance() {
        if (sInstance == null) {
            synchronized (DataBaseManager.class) {
                sInstance = new DataBaseManager();
                sInstance.initializeAppDatabase();
            }
        }
        return sInstance;
    }

    private void initializeAppDatabase() {
//        appDatabase = Room.databaseBuilder(RecordApp.getAppContext(), AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        memoryDatabase = Room.inMemoryDatabaseBuilder(RecordApp.getAppContext(), AppDatabase.class).build();
        DebugDB.setInMemoryRoomDatabase(memoryDatabase);
    }

    public void saveRecord(Record record) {
//        appDatabase.getRecordDao().insert(record);
        memoryDatabase.getRecordDao().insert(record);
    }

    public List<Record> getAllRecords() {
//        return appDatabase.getRecordDao().getAllRecords();
        return memoryDatabase.getRecordDao().getAllRecords();
    }
}
