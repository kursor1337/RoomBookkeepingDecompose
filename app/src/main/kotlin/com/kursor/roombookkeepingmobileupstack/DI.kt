package com.kursor.roombookkeepingmobileupstack

import com.kursor.roombookkeepingmobileupstack.core.coreModule
import com.kursor.roombookkeepingmobileupstack.features.crypto.cryptoModule
import com.kursor.roombookkeepingmobileupstack.features.pokemons.pokemonsModule

val allModules = listOf(
    coreModule(BuildConfig.BACKEND_URL),
    pokemonsModule,
    cryptoModule
)