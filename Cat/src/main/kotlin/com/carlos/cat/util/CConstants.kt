package com.carlos.cat.util

import com.android.SdkConstants.FN_ADB
import com.android.SdkConstants.OS_SDK_PLATFORM_TOOLS_FOLDER
import java.io.File

/**
 * Created by Carlos on 2018/7/3.
 */
object CConstants {

    val appPath = System.getProperty("user.dir")
    val adbPath = appPath + File.separator + OS_SDK_PLATFORM_TOOLS_FOLDER + FN_ADB


    val a = mapOf(Pair("test","test"))
//    val b = a.getor
}