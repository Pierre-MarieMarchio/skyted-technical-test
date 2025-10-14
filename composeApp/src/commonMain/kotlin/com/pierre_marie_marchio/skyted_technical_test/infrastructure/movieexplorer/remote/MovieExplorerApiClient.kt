package com.pierre_marie_marchio.skyted_technical_test.infrastructure.movieexplorer.remote

import com.pierre_marie_marchio.skyted_technical_test.infrastructure.common.interfaces.ApiClient

expect class MovieExplorerApiClient(apiKey: String) : ApiClient {

    override suspend fun get(url: String): String
}