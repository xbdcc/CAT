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

package com.carlos.cat.uiautomator;

import com.android.SdkConstants;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.carlos.cat.util.CConstants;

import java.io.File;

public class DebugBridge {
    private static AndroidDebugBridge sDebugBridge;
    public static String getAdbLocation() {

        // check if adb is present in platform-tools
        String toolDir = System.getenv(SdkConstants.ANDROID_HOME_ENV);
        String adbPath = toolDir + File.separator + SdkConstants.OS_SDK_PLATFORM_TOOLS_FOLDER + SdkConstants.FN_ADB;
        System.out.println("adbp:"+adbPath);
        if (new File(adbPath).exists()) {
            return adbPath;
        }

        // use config adb
        adbPath = CConstants.INSTANCE.getAdbPath();
        if (new File(adbPath).exists()) {
            return adbPath;
        }

        return "adb";
    }

    public static void init() {
        String adbLocation = getAdbLocation();
        System.out.println("adb:"+adbLocation);
        if (adbLocation != null) {
            AndroidDebugBridge.init(false /* debugger support */);
            sDebugBridge = AndroidDebugBridge.createBridge(adbLocation, false);
            //System.out.println(sDebugBridge);
        }
    }

    public static void terminate() {
        if (sDebugBridge != null) {
            sDebugBridge = null;
            AndroidDebugBridge.terminate();
        }
    }

    public static boolean isInitialized() {
        return sDebugBridge != null;
    }

    public static IDevice[] getDevices() {
        return sDebugBridge.getDevices();
    }
}
