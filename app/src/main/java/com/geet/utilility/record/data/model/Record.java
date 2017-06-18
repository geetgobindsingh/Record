package com.geet.utilility.record.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by geetgobindsingh on 16/06/17.
 */

@Entity
public class Record {
    @PrimaryKey
    private String id;

    private String what;

    private String why;

    private Date createdDate;

    private String recordTime;

    public void setId(String id) {
        this.id = id;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public void setWhy(String why) {
        this.why = why;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public String getWhat() {
        return what;
    }

    public String getWhy() {
        return why;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }
}
