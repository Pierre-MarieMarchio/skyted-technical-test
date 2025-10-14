package com.pierre_marie_marchio.skyted_technical_test.infrastructure.common.interfaces

interface ApiClient {
    suspend fun get(url: String): String
}