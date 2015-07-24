/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oss.common.util;

import android.os.Build;
import android.os.Build.VERSION_CODES;


/**
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
