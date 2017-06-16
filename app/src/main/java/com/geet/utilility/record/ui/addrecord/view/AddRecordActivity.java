package com.geet.utilility.record.ui.addrecord.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.geet.utilility.record.R;
import com.geet.utilility.record.ui.base.BaseActivity;

public class AddRecordActivity extends BaseActivity implements View.OnClickListener {

    //region Member variables
    private static final String TAG = AddRecordActivity.class.getSimpleName();
    //endregion

    //region View variables
    EditText mWhatEditText, mWhyEditText;
    Button mStartPauseButton, mCompleteButton;
    //endregion

    //region Activity Lifecycle methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        //view initialisation
        mWhatEditText = (EditText) findViewById(R.id.edit_text_what);
        mWhyEditText = (EditText) findViewById(R.id.edit_text_why);
        mStartPauseButton = (Button) findViewById(R.id.button_start_pause);
        mCompleteButton = (Button) findViewById(R.id.button_complete);

        //adding Click Listener to Buttons button
        mStartPauseButton.setOnClickListener(this);
        mCompleteButton.setOnClickListener(this);
    }
    //endregion

    //region Overriding Click Listener method
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_start_pause: {
                break;
            }
            case R.id.button_complete: {
                break;
            }
        }
    }
    //endregion

    //region Public Static methods
    public static void navigateTo(BaseActivity activity) {
        Intent intent = new Intent(activity, AddRecordActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // do not allow user to go back without completing task - super.onBackPressed();
        showToast(R.string.complete_task_to_exit);
    }
    //endregion

    //region Private Helper methods
    //endregion
}
