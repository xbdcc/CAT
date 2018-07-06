package com.carlos.cat.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Created by Carlos on 2018/7/3.
 */
object LogUtil {

    fun ebc() {

    }

    fun getLogger(clazz: Class<*>): Logger {
        return LoggerFactory.getLogger(clazz)
    }
}