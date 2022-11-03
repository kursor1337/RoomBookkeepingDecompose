package com.kursor.roombookkeepingmobileupstack.features.pokemons.ui.list

import me.aartikov.replica.single.Loadable
import com.kursor.roombookkeepingmobileupstack.features.pokemons.domain.Pokemon
import com.kursor.roombookkeepingmobileupstack.features.pokemons.domain.PokemonId
import com.kursor.roombookkeepingmobileupstack.features.pokemons.domain.PokemonType
import com.kursor.roombookkeepingmobileupstack.features.pokemons.domain.PokemonTypeId

interface PokemonListComponent {

    val types: List<PokemonType>

    val selectedTypeId: PokemonTypeId

    val pokemonsState: Loadable<List<Pokemon>>

    fun onTypeClick(typeId: PokemonTypeId)

    fun onPokemonClick(pokemonId: PokemonId)

    fun onRetryClick()

    fun onRefresh()

    sealed interface Output {
        data class PokemonDetailsRequested(val pokemonId: PokemonId) : Output
    }
}