package com.pierre_marie_marchio.skyted_technical_test.application.common.bases

import com.pierre_marie_marchio.skyted_technical_test.application.common.interfaces.UseCase

abstract class UseCaseBase<in Params, out Response> : UseCase<Params, Response> {

    abstract override suspend fun execute(params: Params): Response

}