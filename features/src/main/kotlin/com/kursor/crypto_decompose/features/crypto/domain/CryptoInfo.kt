package com.kursor.crypto_decompose.features.crypto.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CryptoInfo(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    @SerialName("current_price") val currentPrice: Double,
    @SerialName("price_change_percentage_24h") val priceChange24h: Double
)
