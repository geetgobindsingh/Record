package com.geet.utilility.record.ui.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by geetgobindsingh on 16/06/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView, LifecycleRegistryOwner {

    LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private Toast mToast;

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    public void showToast(@StringRes int message) {
        String toastMessage = getString(message);
        if (mToast == null) {
            mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(toastMessage);
        }
        mToast.show();
    }

    public void showToast(String message) {
        if (mToast == null) {
            mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
        }
        mToast.show();
    }

}
