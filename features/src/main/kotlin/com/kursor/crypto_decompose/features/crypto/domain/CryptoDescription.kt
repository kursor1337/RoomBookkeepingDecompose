package com.kursor.crypto_decompose.features.crypto.domain

import kotlinx.serialization.Serializable

@Serializable
data class CryptoDescription(
    val id: String,
    val symbol: String,
    val description: Description,
    val categories: List<String>
)

@Serializable
data class Description(
    val en: String
)
