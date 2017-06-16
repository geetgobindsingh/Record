package com.geet.utilility.record.ui.base;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by geetgobindsingh on 16/06/17.
 */

public abstract class BaseActivity extends AppCompatActivity {


    private Toast mToast;





    public void showToast(@StringRes int message) {
        String toastMessage = getString(message);
        if (mToast == null) {
            mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(toastMessage);
        }
        mToast.show();
    }
}
