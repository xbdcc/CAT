//package com.carlos.cat.util
//
//import com.android.SdkConstants
//import com.android.ddmlib.AndroidDebugBridge
//import com.android.ddmlib.IDevice
//import org.slf4j.LoggerFactory
//import java.io.File
//
///**
// * Created by Carlos on 2018/7/3.
// */
//object DebugBridge {
//
//    private val logger = LoggerFactory.getLogger(AdbHelper.javaClass)
//
//    private lateinit var androidDebugBridge: AndroidDebugBridge
//
//    fun init() {
//        val adbLocation = getAdbLocation()
//        logger.info("adbLocation:$adbLocation")
//        AndroidDebugBridge.init(false /* debugger support */)
//        androidDebugBridge = AndroidDebugBridge.createBridge(adbLocation, false)
//    }
//
//    fun getAdbLocation(): String {
//
//        // check if adb is present in platform-tools
//        val toolDir = System.getenv(SdkConstants.ANDROID_HOME_ENV)
//        var adbPath = toolDir + File.separator + SdkConstants.OS_SDK_PLATFORM_TOOLS_FOLDER + SdkConstants.FN_ADB
//        if (File(adbPath).exists()) {
//            return adbPath
//        }
//
//        // use config adb
//        adbPath = CConstants.adbPath
//        System.out.println("adb:"+adbPath)
//        if (File(adbPath).exists()) {
//            return adbPath
//        }
//
//        return "adb"
//    }
//
//    fun terminate() {
//        AndroidDebugBridge.terminate()
//    }
//
//    fun getDevices(): Array<IDevice> {
//        return androidDebugBridge.devices
//    }
//
//}