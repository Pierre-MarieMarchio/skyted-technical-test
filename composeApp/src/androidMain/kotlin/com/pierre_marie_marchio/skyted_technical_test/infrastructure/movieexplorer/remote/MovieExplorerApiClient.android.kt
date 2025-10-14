package com.pierre_marie_marchio.skyted_technical_test.infrastructure.movieexplorer.remote

import android.util.Log
import com.pierre_marie_marchio.skyted_technical_test.infrastructure.common.interfaces.ApiClient
import java.net.URL

actual class MovieExplorerApiClient actual constructor(private val apiKey: String) : ApiClient {
    actual override suspend fun get(url: String): String {
        val fullUrl = "$url&apiKey=$apiKey"
        return try {
            URL(fullUrl).readText()
        } catch (e: Exception) {
            Log.d("MainActivity", "Error fetching URL: $fullUrl, ${e.message}")
            ""
        }
    }
}