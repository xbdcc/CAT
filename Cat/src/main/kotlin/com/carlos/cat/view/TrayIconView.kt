package com.carlos.cat.view

import com.carlos.cat.MainApp
import javafx.application.Platform
import javafx.stage.Stage
import java.awt.SystemTray
import java.awt.TrayIcon
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.imageio.ImageIO

/**
 * Created by Carlos on 2018/7/9.
 */
object TrayIconView {

    fun setTrayIconView(stage: Stage) {
        //添加系统托盘图标.
        val tray = SystemTray.getSystemTray()
        val image = ImageIO.read(
            MainApp::class.java!!
                .getResourceAsStream("/img/xbd.jpg")
        )
        val trayIcon = TrayIcon(image, "自动备份工具")
        trayIcon.setToolTip("自动备份工具")
        tray.add(trayIcon)

        trayIcon.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                super.mouseClicked(e)
                if (e.clickCount == 2) {
                    println("双击")
                }else {
                    println("点击了一下")
                    Platform.runLater {
                        stage.isIconified = false
//                        primaryStage.isAlwaysOnTop = true
                    }
                }
            }
        })
    }
}