package com.kursor.crypto_decompose.features.pokemons.data

import me.aartikov.replica.keyed.KeyedReplica
import com.kursor.crypto_decompose.features.pokemons.domain.DetailedPokemon
import com.kursor.crypto_decompose.features.pokemons.domain.Pokemon
import com.kursor.crypto_decompose.features.pokemons.domain.PokemonId
import com.kursor.crypto_decompose.features.pokemons.domain.PokemonTypeId

interface PokemonRepository {

    val pokemonsByTypeReplica: KeyedReplica<PokemonTypeId, List<Pokemon>>

    val pokemonByIdReplica: KeyedReplica<PokemonId, DetailedPokemon>
}