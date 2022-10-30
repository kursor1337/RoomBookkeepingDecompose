package com.kursor.crypto_decompose

import com.kursor.crypto_decompose.core.coreModule
import com.kursor.crypto_decompose.features.crypto.cryptoModule
import com.kursor.crypto_decompose.features.pokemons.pokemonsModule

val allModules = listOf(
    coreModule(BuildConfig.BACKEND_URL),
    pokemonsModule,
    cryptoModule
)