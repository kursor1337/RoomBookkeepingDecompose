package com.kursor.crypto_decompose.features.pokemons.ui.list

import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import kotlinx.parcelize.Parcelize
import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.keyed.keepPreviousData
import com.kursor.crypto_decompose.core.error_handling.ErrorHandler
import com.kursor.crypto_decompose.core.utils.observe
import com.kursor.crypto_decompose.core.utils.persistent
import com.kursor.crypto_decompose.features.pokemons.domain.Pokemon
import com.kursor.crypto_decompose.features.pokemons.domain.PokemonId
import com.kursor.crypto_decompose.features.pokemons.domain.PokemonType
import com.kursor.crypto_decompose.features.pokemons.domain.PokemonTypeId

class RealPokemonListComponent(
    componentContext: ComponentContext,
    private val onOutput: (PokemonListComponent.Output) -> Unit,
    private val pokemonsByTypeReplica: KeyedReplica<PokemonTypeId, List<Pokemon>>,
    errorHandler: ErrorHandler
) : ComponentContext by componentContext, PokemonListComponent {

    override val types = listOf(
        PokemonType.Fire,
        PokemonType.Water,
        PokemonType.Electric,
        PokemonType.Grass,
        PokemonType.Poison
    )

    override var selectedTypeId by mutableStateOf(types[0].id)
        private set

    override val pokemonsState by pokemonsByTypeReplica
        .keepPreviousData()
        .observe(
            lifecycle,
            errorHandler,
            key = { selectedTypeId }
        )

    init {
        persistent(
            save = { PersistentState(selectedTypeId) },
            restore = { state -> selectedTypeId = state.selectedTypeId }
        )
    }

    override fun onTypeClick(typeId: PokemonTypeId) {
        selectedTypeId = typeId
    }

    override fun onPokemonClick(pokemonId: PokemonId) {
        onOutput(PokemonListComponent.Output.PokemonDetailsRequested(pokemonId))
    }

    override fun onRetryClick() {
        pokemonsByTypeReplica.refresh(selectedTypeId)
    }

    override fun onRefresh() {
        pokemonsByTypeReplica.refresh(selectedTypeId)
    }

    @Parcelize
    private data class PersistentState(
        val selectedTypeId: PokemonTypeId
    ) : Parcelable
}