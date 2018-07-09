package com.carlos.cat

import com.android.ddmlib.IDevice
import com.carlos.cat.listener.DeviceChangeListener
import com.carlos.cat.listener.DeviceListener
import com.carlos.cat.util.AdbHelper
import com.carlos.cat.util.LanguageUtil
import com.carlos.cat.util.LogUtil
import com.carlos.cat.view.TrayIconView
import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import javafx.stage.StageStyle
import org.slf4j.LoggerFactory
import java.awt.TrayIcon
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.awt.SystemTray
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JFrame


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

//        LanguageUtil.changeLanguageEnglish(true)
//        val b =LanguageUtil.getString("Cat")
//        println(b)

        AdbHelper.instance.setDeviceListener(this)
        AdbHelper.instance.init()

        val fxmlLoader = FXMLLoader()
        fxmlLoader.location = MainApp::class.java.getResource("/ui/main.fxml")
        primaryStage.icons.add(Image("/img/xbd.jpg"))

        val parent = fxmlLoader.load<Parent>()
        val scene = Scene(parent)

        TrayIconView.setTrayIconView(primaryStage)

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