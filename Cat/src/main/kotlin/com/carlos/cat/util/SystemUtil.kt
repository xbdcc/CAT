package com.carlos.cat.util

import java.io.File

/**
 * Created by Carlos on 2018/6/26.
 */
object SystemUtil {

    fun getOs(): OSEnum {
        val os = System.getProperty("os.name")
        return if (os.startsWith("win") || os.startsWith("Win")) {
            OSEnum.Windows
        } else if (os.contains("mac") || os.contains("Mac")) {
            OSEnum.MAC
        } else {
            OSEnum.Linux
        }
    }
}