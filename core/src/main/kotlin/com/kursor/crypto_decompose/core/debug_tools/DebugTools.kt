package com.kursor.crypto_decompose.core.debug_tools

import okhttp3.Interceptor

interface DebugTools {

    val interceptors: List<Interceptor>

    fun launch()

    fun collectNetworkError(exception: Exception)
}