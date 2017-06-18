package com.geet.utilility.record.ui.recordlist.view;

import com.geet.utilility.record.data.model.Record;
import com.geet.utilility.record.ui.base.IBaseView;

import java.util.List;

/**
 * Created by geetgobindsingh on 18/06/17.
 */

public interface IRecordListView extends IBaseView {
    void showRecordList(List<Record> records);
    void showProgressBar(boolean value);
    void showErrorMessage();
}
