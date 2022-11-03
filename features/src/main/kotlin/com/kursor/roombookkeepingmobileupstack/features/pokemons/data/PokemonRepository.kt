package com.kursor.roombookkeepingmobileupstack.features.pokemons.data

import me.aartikov.replica.keyed.KeyedReplica
import com.kursor.roombookkeepingmobileupstack.features.pokemons.domain.DetailedPokemon
import com.kursor.roombookkeepingmobileupstack.features.pokemons.domain.Pokemon
import com.kursor.roombookkeepingmobileupstack.features.pokemons.domain.PokemonId
import com.kursor.roombookkeepingmobileupstack.features.pokemons.domain.PokemonTypeId

interface PokemonRepository {

    val pokemonsByTypeReplica: KeyedReplica<PokemonTypeId, List<Pokemon>>

    val pokemonByIdReplica: KeyedReplica<PokemonId, DetailedPokemon>
}