package com.kursor.roombookkeepingmobileupstack.features.pokemons

import com.arkivanov.decompose.ComponentContext
import com.kursor.roombookkeepingmobileupstack.core.ComponentFactory
import com.kursor.roombookkeepingmobileupstack.core.network.NetworkApiFactory
import com.kursor.roombookkeepingmobileupstack.features.pokemons.data.PokemonApi
import com.kursor.roombookkeepingmobileupstack.features.pokemons.data.PokemonRepository
import com.kursor.roombookkeepingmobileupstack.features.pokemons.data.PokemonRepositoryImpl
import com.kursor.roombookkeepingmobileupstack.features.pokemons.domain.PokemonId
import com.kursor.roombookkeepingmobileupstack.features.pokemons.ui.PokemonsComponent
import com.kursor.roombookkeepingmobileupstack.features.pokemons.ui.RealPokemonsComponent
import com.kursor.roombookkeepingmobileupstack.features.pokemons.ui.details.PokemonDetailsComponent
import com.kursor.roombookkeepingmobileupstack.features.pokemons.ui.details.RealPokemonDetailsComponent
import com.kursor.roombookkeepingmobileupstack.features.pokemons.ui.list.PokemonListComponent
import com.kursor.roombookkeepingmobileupstack.features.pokemons.ui.list.RealPokemonListComponent
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
