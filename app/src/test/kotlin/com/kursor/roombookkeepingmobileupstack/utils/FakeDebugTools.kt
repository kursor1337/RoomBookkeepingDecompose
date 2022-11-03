package com.kursor.roombookkeepingmobileupstack.utils

import okhttp3.Interceptor
import com.kursor.roombookkeepingmobileupstack.core.debug_tools.DebugTools

class FakeDebugTools : DebugTools {

    override val interceptors: List<Interceptor> = emptyList()

    override fun launch() {
        // do nothing
    }

    override fun collectNetworkError(exception: Exception) {
        // do nothing
    }
}