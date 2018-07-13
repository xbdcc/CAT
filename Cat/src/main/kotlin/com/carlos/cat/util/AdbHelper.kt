package com.carlos.cat.util

import com.carlos.cat.listener.DeviceListener
import com.carlos.cat.uiautomator.DebugBridge
import org.slf4j.LoggerFactory

/**
 * Created by Carlos on 2018/7/3.
 */
class AdbHelper {

    private val logger = LoggerFactory.getLogger(AdbHelper.javaClass)
    private lateinit var deviceListener: DeviceListener

    var serialNumber = ""


    fun setDeviceListener(deviceListener: DeviceListener) {
        this.deviceListener = deviceListener
    }

    fun init() {
        DebugBridge.init()

//        AndroidDebugBridge.addDeviceChangeListener(object : DeviceChangeListener {
//
//            override fun deviceConnected(device: IDevice) {
//                deviceListener.deviceConnect()
//            }
//
//            override fun deviceDisconnected(device: IDevice) {
//                deviceListener.deviceDisconnect()
//            }
//
//            override fun deviceChanged(device: IDevice, int: Int) {
//                println("fdfdf$device fdfdf$int")
//            }
//
//        })
    }

    fun checkDevices() {

        val devices = AdbUtil.getDevices()
        for (device in devices) {

        }


    }

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AdbHelper()
        }
    }

}