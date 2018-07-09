package com.carlos.cat.part

import com.carlos.cat.util.LanguageUtil
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem

/**
 * Created by Carlos on 2018/7/9.
 */
class RootPart {

    fun abc() {


        val menuBar =MenuBar()
        val menu = Menu(LanguageUtil.getString("Edit"))
        val menuItem = MenuItem(LanguageUtil.getString("Undo"))
        menu.items.add(menuItem)

        menuBar.menus.add(menu)
    }

}