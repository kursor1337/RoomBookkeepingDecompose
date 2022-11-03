package com.kursor.roombookkeepingmobileupstack.features.pokemons.ui.details

import me.aartikov.replica.single.Loadable
import com.kursor.roombookkeepingmobileupstack.features.pokemons.domain.DetailedPokemon

interface PokemonDetailsComponent {

    val pokemonState: Loadable<DetailedPokemon>

    fun onRetryClick()

    fun onRefresh()
}