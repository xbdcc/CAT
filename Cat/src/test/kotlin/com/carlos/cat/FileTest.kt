package com.carlos.cat

import com.android.SdkConstants.ANDROID_HOME_ENV
import com.carlos.cat.util.OSEnum
import com.carlos.cat.util.SystemUtil
import org.junit.Test

/**
 * Created by Carlos on 2018/6/26.
 */
class FileTest {

    @Test
    fun test(){
        val type = System.getenv()
        println(type)

        println(System.getProperty("user.dir"))
        println(System.getProperty("os.name"))
        println(System.getProperty("os.arch"))
        println(System.getProperty("os.version"))
        println(System.getProperty("user.name"))
        println(System.getProperty("user.home"))
        println(System.getenv("ANDROID_HOME"))
//        println(System.getenv())
        println(ANDROID_HOME_ENV)
//        println(System.getProperties())
    }

    @Test
    fun getOs(){
        println(SystemUtil.getOs())
        if (OSEnum.MAC.equals(SystemUtil.getOs()))
            println("yes")
    }
}