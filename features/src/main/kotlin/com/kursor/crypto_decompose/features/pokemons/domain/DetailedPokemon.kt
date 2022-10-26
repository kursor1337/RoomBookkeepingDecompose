package com.kursor.crypto_decompose.features.pokemons.domain

data class DetailedPokemon(
    val id: PokemonId,
    val name: String,
    val height: Float,
    val weight: Float,
    val imageUrl: String,
    val types: List<PokemonType>
)