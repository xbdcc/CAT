package com.carlos.cat.vo

import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

/**
 * Created by Carlos on 2018/7/9.
 */
class StepVO{
    var key: StringProperty
    var value: StringProperty

    constructor(key: String, value: String) {
        this.key = SimpleStringProperty(key)
        this.value = SimpleStringProperty(value)
    }

    fun getKey(): String {
        return key.get()
    }

    fun getValue(): String {
        return value.get()
    }
}
