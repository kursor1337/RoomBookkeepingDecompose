package com.kursor.crypto_decompose.features.pokemons.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.kursor.crypto_decompose.features.pokemons.ui.details.PokemonDetailsComponent
import com.kursor.crypto_decompose.features.pokemons.ui.list.PokemonListComponent

interface PokemonsComponent {

    val childStack: ChildStack<*, Child>

    sealed interface Child {
        class List(val component: PokemonListComponent) : Child
        class Details(val component: PokemonDetailsComponent) : Child
    }
}