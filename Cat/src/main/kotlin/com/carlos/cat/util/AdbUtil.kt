package com.carlos.cat.util

import com.carlos.cat.uiautomator.DebugBridge

/**
 * Created by Carlos on 2018/7/3.
 */
object AdbUtil {

    fun getDevices() : List<String> {
        val adb = DebugBridge.getAdbLocation()
        return ShellUtil.executeCommand("$adb devices").responseMessage
    }

    @JvmStatic
    fun main(args: Array<String>) {
        getDevices()
    }
}