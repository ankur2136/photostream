package com.ankur.photostream.utils;

import android.content.Context;
import android.os.Build;

public class Utils {

    private static String LOG_TAG = "UTILS";

    public static boolean isLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static int dpToPixels(Context context, float dps) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dps * scale + 0.5f);
    }

}
