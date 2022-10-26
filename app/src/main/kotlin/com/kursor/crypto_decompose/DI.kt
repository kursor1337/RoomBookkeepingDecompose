package com.kursor.crypto_decompose

import com.kursor.crypto_decompose.core.coreModule
import com.kursor.crypto_decompose.features.pokemons.pokemonsModule
import ru.mobileup.template.BuildConfig

val allModules = listOf(
    coreModule(BuildConfig.BACKEND_URL),
    pokemonsModule
)