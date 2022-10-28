package com.kursor.crypto_decompose.features.crypto.data

import com.kursor.crypto_decompose.features.crypto.domain.CryptoDescription
import com.kursor.crypto_decompose.features.crypto.domain.CryptoInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApi {

    @GET("coins/markets")
    suspend fun getCryptoCurrencyInfoList(
        @Query("vs_currency") vsCurrency: String
    ): List<CryptoInfo>

    @GET("coins/{id}")
    suspend fun getCryptoCurrencyDescription(
        @Path("id") id: String,
        @Query("localization") localization: Boolean = false
    ): CryptoDescription

}