package com.kursor.roombookkeepingmobileupstack.features.pokemons.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.kursor.roombookkeepingmobileupstack.core.theme.AppTheme
import com.kursor.roombookkeepingmobileupstack.core.utils.createFakeChildStack
import com.kursor.roombookkeepingmobileupstack.features.pokemons.ui.details.PokemonDetailsUi
import com.kursor.roombookkeepingmobileupstack.features.pokemons.ui.list.FakePokemonListComponent
import com.kursor.roombookkeepingmobileupstack.features.pokemons.ui.list.PokemonListUi

@Composable
fun PokemonsUi(
    component: PokemonsComponent,
    modifier: Modifier = Modifier
) {
    Children(component.childStack, modifier) { child ->
        when (val instance = child.instance) {
            is PokemonsComponent.Child.List -> PokemonListUi(instance.component)
            is PokemonsComponent.Child.Details -> PokemonDetailsUi(instance.component)
        }
    }
}

@Preview
@Composable
fun PokemonsUiPreview() {
    AppTheme {
        PokemonsUi(FakePokemonsComponent())
    }
}

class FakePokemonsComponent : PokemonsComponent {

    override val childStack = createFakeChildStack(
        PokemonsComponent.Child.List(FakePokemonListComponent())
    )
}