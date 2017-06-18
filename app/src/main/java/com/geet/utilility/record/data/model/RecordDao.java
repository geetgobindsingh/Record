package com.geet.utilility.record.data.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by geetgobindsingh on 16/06/17.
 */

@Dao
public interface RecordDao {

    @Query("SELECT * FROM record ORDER BY date(createdDate) DESC")
    List<Record> getAllRecords();

    @Query("SELECT * FROM record where id = :id")
    Record getRecord(long id);

    @Insert
    void insert(Record record);

    @Delete
    void delete(Record record);
}
