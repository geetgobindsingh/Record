package com.geet.utilility.record.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import com.geet.utilility.record.data.model.Record;
import com.geet.utilility.record.data.model.RecordDao;
import com.geet.utilility.record.data.converters.DateConverter;

/**
 * Created by geetgobindsingh on 16/06/17.
 */

@Database(entities = {Record.class}, version = AppDatabase.DATABASE_VERSION)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "APP_DATABASE";

    public abstract RecordDao getRecordDao();
}
