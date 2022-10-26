package com.kursor.crypto_decompose.core.network

import retrofit2.Converter
import com.kursor.crypto_decompose.core.error_handling.DeserializationException

/**
 * Converts exceptions of JSON-framework to [DeserializationException].
 */
class ErrorHandlingConverter<F : Any, T : Any>(private val converter: Converter<F, T>) :
    Converter<F, T> {

    override fun convert(value: F): T? {
        return try {
            converter.convert(value)
        } catch (e: Exception) {
            throw DeserializationException(e)
        }
    }
}
