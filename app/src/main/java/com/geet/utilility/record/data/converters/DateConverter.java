package com.geet.utilility.record.data.converters;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by geetgobindsingh on 16/06/17.
 */

public class DateConverter {

    @TypeConverter
    public static Date fromTimeStamp(Long timeStamp) {
        return timeStamp == null ? null : new Date(timeStamp);
    }

    @TypeConverter
    public static Long fromDate(Date date) {
        return date == null ? null : date.getTime();
    }
}
