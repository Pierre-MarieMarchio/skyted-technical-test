package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.services.loadImageFromUrl

@Composable
fun MovieListItemImage(
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
                    .width(80.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        } ?: MovieListItemImagePlaceholder()
    } else {
        MovieListItemImagePlaceholder()
    }
}