package com.example.gamesproj.utils.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.IOException

/**
 * ContextExtension
 *
 */

@Throws(IOException::class)
fun Context.getBitmapFromUri(uri: Uri, options: BitmapFactory.Options? = null): Bitmap? {
    val parcelFileDescriptor = contentResolver.openFileDescriptor(uri, "r")
    val fileDescriptor = parcelFileDescriptor?.fileDescriptor

    val image: Bitmap? = when (options) {
        null -> BitmapFactory.decodeFileDescriptor(fileDescriptor)
        else -> BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options)
    }

    parcelFileDescriptor?.close()
    return image
}

fun Context.getImageHgtWdt(uri: Uri): Pair<Int, Int> {
    val opt = BitmapFactory.Options()

    /* by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded.
    If you try the use the bitmap here, you will get null.*/
    opt.inJustDecodeBounds = true
    @Suppress("UNUSED_VARIABLE") val bm = getBitmapFromUri(uri, opt)

    var actualHgt = (opt.outHeight).toFloat()
    var actualWdt = (opt.outWidth).toFloat()
    val maxHeight = 720f
    val maxWidth = 1280f
    var imgRatio = actualWdt / actualHgt
    val maxRatio = maxWidth / maxHeight

    // Width and height values are set maintaining the aspect ratio of the image.
    when {
        actualHgt > maxHeight || actualWdt > maxWidth -> {
            when {
                imgRatio < maxRatio -> {
                    imgRatio = maxHeight / actualHgt
                    actualWdt = (imgRatio * actualWdt)
                    actualHgt = maxHeight
                }

                imgRatio > maxRatio -> {
                    imgRatio = maxWidth / actualWdt
                    actualHgt = (imgRatio * actualHgt)
                    actualWdt = maxWidth
                }

                else -> {
                    actualHgt = maxHeight
                    actualWdt = maxWidth
                }
            }
        }
    }

    return Pair(actualHgt.toInt(), actualWdt.toInt())
}