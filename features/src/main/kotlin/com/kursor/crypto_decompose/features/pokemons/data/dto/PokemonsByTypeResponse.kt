package com.kursor.crypto_decompose.features.pokemons.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.kursor.crypto_decompose.features.pokemons.domain.Pokemon

@Serializable
class PokemonsByTypeResponse(
    @SerialName("pokemon") val pokemons: List<PokemonWrapperResponse>
)

fun PokemonsByTypeResponse.toDomain(): List<Pokemon> {
    return pokemons.map { it.pokemon.toDomain() }
}
