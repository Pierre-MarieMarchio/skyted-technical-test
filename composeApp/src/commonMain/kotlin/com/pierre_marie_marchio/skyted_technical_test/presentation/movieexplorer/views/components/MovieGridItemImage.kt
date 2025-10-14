package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.services.loadImageFromUrl

@Composable
fun MovieGridItemImage(
    imageUrl: String?,
    title: String
) {
    if (!imageUrl.isNullOrEmpty()) {
        var bitmap by remember { mutableStateOf<ImageBitmap?>(null) }

        LaunchedEffect(imageUrl) {
            bitmap = loadImageFromUrl(imageUrl)
        }

        bitmap?.let {
            Image(
                bitmap = it,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        } ?: MovieGridItemImagePlaceholder()
    } else {
        MovieGridItemImagePlaceholder()
    }
}