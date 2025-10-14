package com.pierre_marie_marchio.skyted_technical_test.presentation.common.services

import androidx.compose.ui.graphics.ImageBitmap


expect suspend fun loadImageFromUrl(url: String): ImageBitmap?