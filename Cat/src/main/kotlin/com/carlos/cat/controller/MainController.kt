package com.carlos.cat.controller

import com.android.ddmlib.IDevice
import com.carlos.cat.part.DetailsPart
import com.carlos.cat.part.ImagePart
import com.carlos.cat.uiautomator.DebugBridge
import com.carlos.cat.uiautomator.UiAutomatorModel
import com.carlos.cat.util.LanguageUtil
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.canvas.Canvas
import javafx.scene.control.Label
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.StackPane
import java.io.File
import java.net.URL
import java.util.*

/**
 * Created by Carlos on 2018/6/26.
 */
class MainController : Initializable {

    @FXML
    private lateinit var menuBar: MenuBar
    @FXML
    private lateinit var anchorPane: AnchorPane

    @FXML
    private lateinit var imageView: ImageView
    @FXML
    private lateinit var title: Label

    lateinit var detailsPart: DetailsPart


    override fun initialize(location: URL?, resources: ResourceBundle?) {
        menuBar.isUseSystemMenuBar = true

//        val menuBar =MenuBar()
        val menu = Menu(LanguageUtil.getString("Edit"))
        val menuItem = MenuItem(LanguageUtil.getString("Undo"))
        menu.items.add(menuItem)

        menuBar.menus.clear()
        menuBar.menus.add(menu)


        val canvas = Canvas()
        val holder = StackPane()
        canvas.setTranslateX(0.0)
        canvas.setTranslateY(0.0)

        title.text = LanguageUtil.getString("Cat")
//        anchorPane.getChildren().add(imageView)
        anchorPane.getChildren().add(canvas)
//        anchorPane.getChildren().add(holder)

        detailsPart = DetailsPart(anchorPane)
        detailsPart.init()

        val mUiAutomatorView = ImagePart(this,anchorPane, imageView)
    }

//    fun click() {

//        detailsPart.addItems()

        // to prevent blocking the ui thread, we do the saving in the other thread.
//        AdbHelper.instance.init()

//        Thread.sleep(1000)
//        val device = pickDevice()
//
////        println(device!!.serialNumber)
//        if (device != null) {
//
//            val result = UiAutomatorHelper.takeSnapshot(device, true)
//            //
//            setModel(result.model, result.uiHierarchy, result.screenshot)
//        }

//    }

    fun setModel(model: UiAutomatorModel, modelBackingFile: File, screenshot: Image) {
        println(screenshot)
        imageView.image = screenshot
    }

    private fun pickDevice(): IDevice? {
        val devices = DebugBridge.getDevices()
        if (devices.isEmpty()) {
            return null
        } else if (devices.size == 1) {
            return devices[0]
        }
        return null
    }


//    val menuBar: MenuBar
//    init {
//
//    }

}