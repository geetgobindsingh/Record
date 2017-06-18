package com.geet.utilility.record.ui.addrecord.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;

import com.geet.utilility.record.R;
import com.geet.utilility.record.ui.addrecord.viewmodel.AddRecordViewModel;
import com.geet.utilility.record.ui.base.BaseActivity;
import com.geet.utilility.record.utils.AppConstants;
import com.geet.utilility.record.utils.CommonUtil;

public class AddRecordActivity extends BaseActivity implements IAddRecordView, View.OnClickListener {

    //region Member variables
    private static final String TAG = AddRecordActivity.class.getSimpleName();
    private static final int START_STATE = 0;
    private static final int PAUSE_STATE = 1;
    private AddRecordViewModel addRecordViewModel;
    private StringBuffer timerString = new StringBuffer();
    private int buttonStatus;
    //endregion

    //region View variables
    private EditText mWhatEditText, mWhyEditText;
    private Button mStartPauseButton, mCompleteButton;
    private Chronometer mChronometer;
    //endregion

    //region Activity Lifecycle methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        //ViewHolder initialisation
        addRecordViewModel = ViewModelProviders.of(this).get(AddRecordViewModel.class);
        addRecordViewModel.attachView(AddRecordActivity.this);

        //view initialisation
        mWhatEditText = (EditText) findViewById(R.id.edit_text_what);
        mWhyEditText = (EditText) findViewById(R.id.edit_text_why);
        mStartPauseButton = (Button) findViewById(R.id.button_start_pause);
        mCompleteButton = (Button) findViewById(R.id.button_complete);
        mChronometer = (Chronometer) findViewById(R.id.invisibleChronometer);

        //adding Click Listener to Buttons button
        mStartPauseButton.setOnClickListener(this);
        mCompleteButton.setOnClickListener(this);


        //get Chronometer listener
        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                timerString.replace(0, timerString.capacity(), (String) chronometer.getText());
                supportInvalidateOptionsMenu();
            }
        });

        buttonStatus = START_STATE;
    }

    private void startTimer() {

        if (addRecordViewModel.getStartDate() == null) {
            // If the start date is not defined, it's a new ViewModel so set it.
            long startTime = SystemClock.elapsedRealtime();
            addRecordViewModel.setStartDate(startTime);
            mChronometer.setBase(startTime);
        } else {
            // Otherwise the ViewModel has been retained, set the chronometer's base to the original
            // starting time.
            mChronometer.setBase(addRecordViewModel.getStartDate());
        }
        mChronometer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_record, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem timerItem = menu.findItem(R.id.timer);
        if (timerItem != null) {
            timerItem.setTitle(timerString);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    //endregion

    //region Overriding Click Listener method
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_start_pause: {
                switch (buttonStatus) {
                    case START_STATE: {
                        startTimer();
                        mStartPauseButton.setText(R.string.pause);
                        buttonStatus = PAUSE_STATE;
                        enableCompleteButton();
                        break;
                    }
                    case PAUSE_STATE: {
                        stopTimer();
                        mStartPauseButton.setText(R.string.resume);
                        buttonStatus = START_STATE;
                        break;
                    }
                }
                break;
            }
            case R.id.button_complete: {
                if(isValidDataEntered()) {
                    stopTimer();
                    addRecordViewModel.saveRecord(mWhatEditText.getText().toString(), mWhyEditText.getText().toString(), mChronometer.getText().toString());
                }
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

    //region Ovveriding I AddRecordView methods
    @Override
    public void recordSavedSuccessfully() {
        showToast(R.string.record_completed_successfully);
        finish();
    }

    @Override
    public void recordSavingError(String message) {
        showToast(message);
    }
    //endregion

    //region Private Helper methods
    private void enableCompleteButton() {
        mCompleteButton.setEnabled(true);
        mCompleteButton.setTextColor(getResources().getColor(R.color.colorText));
    }

    private boolean isValidDataEntered() {
        boolean idValid = false;
        StringBuilder whatString = new StringBuilder(mWhatEditText.getText().toString());
        StringBuilder whyString = new StringBuilder(mWhyEditText.getText().toString());
        if(CommonUtil.isNotEmpty(whatString.toString())) {
            if(whatString.toString().length() <= AppConstants.WHAT_STRING_MAX_LENGTH) {
                mWhatEditText.setError(null);
                if(CommonUtil.isNotEmpty(whyString.toString())) {
                    if(whyString.toString().length() <= AppConstants.WHY_STRING_MAX_LENGTH) {
                        mWhyEditText.setError(null);
                        idValid = true;
                    }
                    else {
                        mWhyEditText.setError(getString(R.string.please_write_within_provided_limit));
                    }
                }
                else {
                    mWhyEditText.setError(getString(R.string.please_write));
                }
            }
            else {
                mWhatEditText.setError(getString(R.string.please_write_within_provided_limit));
            }
        }
        else {
            mWhatEditText.setError(getString(R.string.please_write));
        }
        return idValid;
    }

    private void stopTimer() {
        mChronometer.stop();
    }
    //endregion
}
