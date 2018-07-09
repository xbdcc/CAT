package com.carlos.cat.util

import java.util.*
import kotlin.text.Charsets.UTF_8

/**
 * Created by Carlos on 2018/7/6.
 */
object LanguageUtil {

    fun changeLanguageEnglish(boolean: Boolean) {
        if (boolean) {
            Locale.setDefault(Locale("en"))
        }else {
            Locale.setDefault(Locale("zh"))
        }
    }

    fun getString(name: String): String {
        return getString(name,CConstants.languageFileName)
    }

    fun getString(name: String,languageFileName: String): String {
        val resourceBundle = ResourceBundle.getBundle(languageFileName)
        return String(resourceBundle.getString(name).toByteArray(charset("ISO-8859-1")), UTF_8)
    }

}