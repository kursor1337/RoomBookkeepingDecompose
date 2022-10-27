package com.kursor.crypto_decompose.features.crypto.domain

enum class Currency(val id: String, val symbol: Char) {
    USD(id = "usd", symbol = '$'),
    EUR(id = "eur", symbol = '€')
}