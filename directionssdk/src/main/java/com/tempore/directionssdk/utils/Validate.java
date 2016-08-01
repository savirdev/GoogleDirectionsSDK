package com.tempore.directionssdk.utils;/*
 * Copyright (c) 2016 Tempore, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.tempore.directionssdk.DirectionsSdk;
import com.tempore.directionssdk.utils.exceptions.DirectionsSdkNotInitializedException;

import java.util.Collection;

public final class Validate {

    private static final String NO_INTERNET_PERMISSION_REASON =
            "No internet permissions granted for the app, please add " +
                    "<uses-permission android:name=\"android.permission.INTERNET\" /> " +
                    "to your AndroidManifest.xml.";

    private static final String TAG = Validate.class.getName();

    public static void notNull(Object arg, String name) {
        if (arg == null) {
            throw new NullPointerException("Argument '" + name + "' cannot be null");
        }
    }

    public static <T> void notEmpty(Collection<T> container, String name) {
        if (container.isEmpty()) {
            throw new IllegalArgumentException("Container '" + name + "' cannot be empty");
        }
    }

    public static <T> void containsNoNulls(Collection<T> container, String name) {
        Validate.notNull(container, name);
        for (T item : container) {
            if (item == null) {
                throw new NullPointerException("Container '" + name +
                        "' cannot contain null values");
            }
        }
    }

    public static void containsNoNullOrEmpty(Collection<String> container, String name) {
        Validate.notNull(container, name);
        for (String item : container) {
            if (item == null) {
                throw new NullPointerException("Container '" + name +
                        "' cannot contain null values");
            }
            if (item.length() == 0) {
                throw new IllegalArgumentException("Container '" + name +
                        "' cannot contain empty values");
            }
        }
    }

    public static <T> void notEmptyAndContainsNoNulls(Collection<T> container, String name) {
        Validate.containsNoNulls(container, name);
        Validate.notEmpty(container, name);
    }


    public static void sdkInitialized() {
        if (!DirectionsSdk.isInitialized()) {
            try {
                throw new DirectionsSdkNotInitializedException("The SDK has not been initialized, make sure to call " + "Directions.init() first.");
            } catch (DirectionsSdkNotInitializedException e) {
                e.printStackTrace();
            }
        }
    }

    public static String hasApiKey() {
        String id = DirectionsSdk.getApiKey();
        if (id == null) {
            throw new IllegalStateException("No ApiKey found, please set the Api Key.");
        }
        return id;
    }

    public static void hasInternetPermissions(Context context) {
        Validate.hasInternetPermissions(context, true);
    }

    public static void hasInternetPermissions(Context context, boolean shouldThrow) {
        Validate.notNull(context, "context");
        if (context.checkCallingOrSelfPermission(Manifest.permission.INTERNET) ==
                PackageManager.PERMISSION_DENIED) {
            if (shouldThrow) {
                throw new IllegalStateException(NO_INTERNET_PERMISSION_REASON);
            } else {
                Log.w(TAG, NO_INTERNET_PERMISSION_REASON);
            }
        }
    }
}