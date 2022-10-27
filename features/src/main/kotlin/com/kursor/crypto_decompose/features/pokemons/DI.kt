package com.kursor.crypto_decompose.features.pokemons

import com.arkivanov.decompose.ComponentContext
import com.kursor.crypto_decompose.core.ComponentFactory
import com.kursor.crypto_decompose.core.network.NetworkApiFactory
import com.kursor.crypto_decompose.features.crypto.ui.CryptoComponent
import com.kursor.crypto_decompose.features.crypto.ui.RealCryptoComponent
import com.kursor.crypto_decompose.features.pokemons.data.PokemonApi
import com.kursor.crypto_decompose.features.pokemons.data.PokemonRepository
import com.kursor.crypto_decompose.features.pokemons.data.PokemonRepositoryImpl
import com.kursor.crypto_decompose.features.pokemons.domain.PokemonId
import com.kursor.crypto_decompose.features.pokemons.ui.PokemonsComponent
import com.kursor.crypto_decompose.features.pokemons.ui.RealPokemonsComponent
import com.kursor.crypto_decompose.features.pokemons.ui.details.PokemonDetailsComponent
import com.kursor.crypto_decompose.features.pokemons.ui.details.RealPokemonDetailsComponent
import com.kursor.crypto_decompose.features.pokemons.ui.list.PokemonListComponent
import com.kursor.crypto_decompose.features.pokemons.ui.list.RealPokemonListComponent
import me.aartikov.replica.algebra.withKey
import org.koin.core.component.get
import org.koin.dsl.module

val pokemonsModule = module {
    single<PokemonApi> { get<NetworkApiFactory>().createUnauthorizedApi() }
    single<PokemonRepository> { PokemonRepositoryImpl(get(), get()) }
}

fun ComponentFactory.createPokemonsComponent(
    componentContext: ComponentContext
): PokemonsComponent {
    return RealPokemonsComponent(componentContext, get())
}

fun ComponentFactory.createPokemonListComponent(
    componentContext: ComponentContext,
    onOutput: (PokemonListComponent.Output) -> Unit
): PokemonListComponent {
    val pokemonsByTypeReplica = get<PokemonRepository>().pokemonsByTypeReplica
    return RealPokemonListComponent(componentContext, onOutput, pokemonsByTypeReplica, get())
}

fun ComponentFactory.createPokemonDetailsComponent(
    componentContext: ComponentContext,
    pokemonId: PokemonId
): PokemonDetailsComponent {
    val pokemonReplica = get<PokemonRepository>().pokemonByIdReplica.withKey(pokemonId)
    return RealPokemonDetailsComponent(componentContext, pokemonReplica, get())
}
