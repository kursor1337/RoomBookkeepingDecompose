package com.kursor.crypto_decompose.features.pokemons.ui.details

import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.kursor.crypto_decompose.core.error_handling.ErrorHandler
import com.kursor.crypto_decompose.core.utils.observe
import com.kursor.crypto_decompose.features.pokemons.domain.DetailedPokemon
import me.aartikov.replica.single.Replica

class RealPokemonDetailsComponent(
    componentContext: ComponentContext,
    private val pokemonReplica: Replica<DetailedPokemon>,
    errorHandler: ErrorHandler
) : ComponentContext by componentContext, PokemonDetailsComponent {

    override val pokemonState by pokemonReplica.observe(lifecycle, errorHandler)

    override fun onRetryClick() {
        pokemonReplica.refresh()
    }

    override fun onRefresh() {
        pokemonReplica.refresh()
    }
}