package com.carlos.cat

import com.carlos.cat.listener.DeviceListener
import com.carlos.cat.util.AdbHelper
import com.carlos.cat.util.CConstants
import com.carlos.cat.util.LogUtil
import com.carlos.cat.view.TrayIconView
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage


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
        println(CConstants.FXML_MAIN)
        fxmlLoader.location = MainApp::class.java.getResource(CConstants.FXML_MAIN)
        primaryStage.icons.add(Image(CConstants.IMG_ICON))

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