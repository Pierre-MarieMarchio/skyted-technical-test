package com.pierre_marie_marchio.skyted_technical_test.application.common.interfaces

interface UseCase<in Params, out Response> {
    suspend fun execute(params: Params): Response
}