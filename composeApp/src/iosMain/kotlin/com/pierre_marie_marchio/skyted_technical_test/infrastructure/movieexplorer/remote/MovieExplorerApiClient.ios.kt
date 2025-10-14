package com.pierre_marie_marchio.skyted_technical_test.infrastructure.movieexplorer.remote

import com.pierre_marie_marchio.skyted_technical_test.infrastructure.common.interfaces.ApiClient
import kotlinx.cinterop.BetaInteropApi
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

actual class MovieExplorerApiClient actual constructor(private val apiKey: String) : ApiClient {
    @OptIn(BetaInteropApi::class)
    actual override suspend fun get(url: String): String = suspendCancellableCoroutine { cont ->
        val nsUrl = NSURL(string = url)!!
        val request = NSURLRequest.requestWithURL(nsUrl)
        val task = NSURLSession.sharedSession.dataTaskWithRequest(request) { data, _, error ->
            if (error != null) {
                cont.resumeWithException(Exception(error.localizedDescription))
            } else {
                val result = NSString.create(data!!, NSUTF8StringEncoding)!!.toString()
                cont.resume(result)
            }
        }
        cont.invokeOnCancellation { task.cancel() }
        task.resume()
    }
}