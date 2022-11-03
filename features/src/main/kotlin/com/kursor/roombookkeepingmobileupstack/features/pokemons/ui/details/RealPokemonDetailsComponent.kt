package com.kursor.roombookkeepingmobileupstack.features.pokemons.ui.details

import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.kursor.roombookkeepingmobileupstack.core.error_handling.ErrorHandler
import com.kursor.roombookkeepingmobileupstack.core.utils.observe
import com.kursor.roombookkeepingmobileupstack.features.pokemons.domain.DetailedPokemon
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