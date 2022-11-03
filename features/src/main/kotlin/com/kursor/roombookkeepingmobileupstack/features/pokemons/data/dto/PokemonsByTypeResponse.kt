package com.kursor.roombookkeepingmobileupstack.features.pokemons.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.kursor.roombookkeepingmobileupstack.features.pokemons.domain.Pokemon

@Serializable
class PokemonsByTypeResponse(
    @SerialName("pokemon") val pokemons: List<PokemonWrapperResponse>
)

fun PokemonsByTypeResponse.toDomain(): List<Pokemon> {
    return pokemons.map { it.pokemon.toDomain() }
}
