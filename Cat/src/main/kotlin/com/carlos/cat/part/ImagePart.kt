package com.carlos.cat.part

import javafx.scene.canvas.Canvas
import javafx.scene.image.ImageView
import javafx.scene.input.MouseButton
import javafx.scene.layout.Pane

/**
 * Created by Carlos on 2018/7/4.
 */
class ImagePart {

    private val pane: Pane
    private val canvas: ImageView

    constructor(pane: Pane, canvas: ImageView) {
        this.pane = pane
        this.canvas = canvas

        canvas.setOnMouseReleased { event ->
            val button = event.button
            when(button) {
                MouseButton.PRIMARY-> println("Left Button Pressed")
                MouseButton.SECONDARY-> println("Right Button Pressed")
            }
        }

    }

}