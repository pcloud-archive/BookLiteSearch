package com.pcloud.booklitesearch.util

import android.graphics.Bitmap
import com.squareup.picasso.Transformation

val ReSizeTransformation = object:Transformation {
    private val targetWidth:Int = 300
    override fun transform(source: Bitmap): Bitmap {
        val aspectRatio:Double = source.height.toDouble() / source.width.toDouble()
        val targetHeight:Int = targetWidth * aspectRatio.toInt()
        val result:Bitmap = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false)
        if (result != source) {
            source.recycle()
        }
        return result
    }

    override fun key(): String {
        return "resizeTransformation#" + System.currentTimeMillis()
    }
}