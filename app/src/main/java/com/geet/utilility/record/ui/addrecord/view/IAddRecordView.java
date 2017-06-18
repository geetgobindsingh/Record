package com.geet.utilility.record.ui.addrecord.view;

import com.geet.utilility.record.ui.base.IBaseView;

/**
 * Created by geetgobindsingh on 18/06/17.
 */

public interface IAddRecordView extends IBaseView {
    void recordSavedSuccessfully();

    void recordSavingError(String message);
}
