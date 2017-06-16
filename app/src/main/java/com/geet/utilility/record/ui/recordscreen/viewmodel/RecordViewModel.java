package com.geet.utilility.record.ui.recordscreen.viewmodel;

import android.support.annotation.Nullable;

import com.geet.utilility.record.ui.base.BaseViewModel;

/**
 * Created by geetgobindsingh on 16/06/17.
 */

public class RecordViewModel extends BaseViewModel {
    @Nullable
    private Long startDate;

    @Nullable
    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(final long startDate) {
        this.startDate = startDate;
    }
}
