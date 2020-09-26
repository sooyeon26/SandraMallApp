package com.sooyeon.sandramall.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable

private fun borderBackground(
    borderColor: String = "#1F000000",
    bgColor: String = "#FFFFFF",
    borderWidthLeft: Int = 0,
    borderWidthTop: Int = 0,
    borderWidthRight: Int = 0,
    borderWidthBottom: Int = 0
): LayerDrawable {

    val drawables = arrayOf<Drawable>(
        ColorDrawable(Color.parseColor(borderColor)),
        ColorDrawable(Color.parseColor(bgColor))
    )

    val layerDrawable = LayerDrawable(drawables)

    layerDrawable.setLayerInset(
        1,
        borderWidthLeft,
        borderWidthTop,
        borderWidthRight,
        borderWidthBottom
    )

    return layerDrawable
}

fun borderLeft(
    color: String = "#1F000000",
    width: Int
) = borderBackground(borderColor = color, borderWidthLeft = width)

fun borderTop(
    color: String = "#1F000000",
    width: Int
) = borderBackground(borderColor = color, borderWidthTop = width)

fun borderRight(
    color: String = "#1F000000",
    width: Int
) = borderBackground(borderColor = color, borderWidthRight = width)

fun borderBottom(
    color: String = "#1F000000",
    width: Int
) = borderBackground(borderColor = color, borderWidthBottom = width)
