package com.carlos.cat

import com.android.ddmlib.IDevice
import com.carlos.cat.listener.DeviceChangeListener
import com.carlos.cat.listener.DeviceListener
import com.carlos.cat.util.AdbHelper
import com.carlos.cat.util.LogUtil
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.stage.StageStyle
import org.slf4j.LoggerFactory

/**￼
 * Created by Carlos on 2018/6/14.
 */
class MainApp : Application(),DeviceListener {
    override fun deviceConnect() {
        println("connected")
    }

    override fun deviceDisconnect() {
        println("disconnected")
    }

    private val logger = LogUtil.getLogger(MainApp::class.java)
//    private val logger = LoggerFactory.getLogger(MainApp.javaClass)

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {

        logger.info("hellotest")


        AdbHelper.instance.setDeviceListener(this)
        AdbHelper.instance.init()

        val fxmlLoader = FXMLLoader()
        fxmlLoader.location = MainApp::class.java.getResource("/ui/main.fxml")

        val parent = fxmlLoader.load<Parent>()
        val scene = Scene(parent)

        primaryStage.scene = scene
        primaryStage.show()

    }

    /**
     * Init config
     */
    fun initConfig() {

    }

    fun initLogback() {

    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            launch(MainApp::class.java, *args)
        }
    }

}