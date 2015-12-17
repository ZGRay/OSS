package com.oss.common.util;

import android.os.Build;
import android.os.Build.VERSION_CODES;


/**
 * @author Lei.Zhang
 * @email marvylei@aliyun.com
 * Created by Lei.Zhang on 2015/7/22.
 * Class containing some static utility methods.
 */
public class Utils {
    private Utils() {
    }

    ;


    /**
     * Added in API level 8
     * June 2010: Android 2.2
     */
    public static boolean hasFroyo() {
        // Can use static final constants like FROYO, declared in later versions
        // of the OS since they are inlined at compile time. This is guaranteed behavior.
        return Build.VERSION.SDK_INT >= VERSION_CODES.FROYO;
    }

    /**
     * Added in API level 9
     * November 2010: Android 2.3*
     */
    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD;
    }

    /**
     * Added in API level 11
     * February 2011: Android 3.0.*
     */
    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB;
    }

    /**
     * Added in API level 12
     * May 2011: Android 3.1.*
     */
    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB_MR1;
    }

    /**
     * Added in API level 16
     * June 2012: Android 4.1.
     */
    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN;
    }

    /**
     * Added in API level 19
     * October 2013: Android 4.4, KitKat, another tasty treat.
     *
     * @return
     */
    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.KITKAT;
    }
}
