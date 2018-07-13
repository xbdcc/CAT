package com.carlos.cat

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
import javafx.stage.Stage
import org.junit.Test

/**
 * Created by Carlos on 2018/7/11.
 */
class MenuTest : Application() {

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {

        val menuBar = MenuBar()
        val menuItem = MenuItem("hello")
        val menus = Menu("menu")
        menus.items.addAll(menuItem,menuItem)
        menuBar.menus.add(menus)

        val scene = Scene(menuBar)
        primaryStage.scene = scene
        primaryStage.show()

    }

    @Test
    fun startApp() {
        launch(MenuTest::class.java)
    }

}