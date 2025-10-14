package com.pierre_marie_marchio.skyted_technical_test.presentation.common.services

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Image
import platform.Foundation.NSData
import platform.Foundation.NSURL
import platform.Foundation.dataWithContentsOfURL
import platform.Foundation.getBytes

@OptIn(ExperimentalForeignApi::class)
actual suspend fun loadImageFromUrl(url: String): ImageBitmap? {
    return withContext(Dispatchers.IO) {
        try {
            val nsUrl = NSURL.URLWithString(url) ?: return@withContext null
            val data = NSData.dataWithContentsOfURL(nsUrl) ?: return@withContext null

            val byteArray = ByteArray(data.length.toInt())
            if (byteArray.isNotEmpty()) {
                byteArray.usePinned { pinned ->
                    data.getBytes(pinned.addressOf(0), data.length.toULong())
                }
            }

            val skiaImage = Image.makeFromEncoded(byteArray)
            skiaImage.toComposeImageBitmap()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }
}