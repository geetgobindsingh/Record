package com.geet.utilility.record.utils;

import android.content.Context;
import android.os.Build;

/**
 * Created by geetgobindsingh on 18/06/17.
 */
public class CommonUtil {
    public static boolean isNotEmpty(String value) {
        return value != null && value.length() != 0;
    }

    public static int dpToPx(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * scale);
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
