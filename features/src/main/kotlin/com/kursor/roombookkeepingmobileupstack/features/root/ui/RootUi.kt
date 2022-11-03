package com.kursor.roombookkeepingmobileupstack.features.root.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kursor.roombookkeepingmobileupstack.core.message.ui.FakeMessageComponent
import com.kursor.roombookkeepingmobileupstack.core.message.ui.MessageUi
import com.kursor.roombookkeepingmobileupstack.core.theme.AppTheme
import com.kursor.roombookkeepingmobileupstack.core.utils.createFakeChildStack
import com.kursor.roombookkeepingmobileupstack.features.crypto.ui.CryptoUi
import com.kursor.roombookkeepingmobileupstack.features.pokemons.ui.FakePokemonsComponent
import com.kursor.roombookkeepingmobileupstack.features.pokemons.ui.PokemonsUi

@Composable
fun RootUi(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    SystemBarColors()

    Children(component.childStack, modifier) { child ->
        when (val instance = child.instance) {
            is RootComponent.Child.Pokemons -> PokemonsUi(instance.component)
            is RootComponent.Child.Crypto -> CryptoUi(instance.component)
        }
    }

    MessageUi(
        component = component.messageComponent,
        modifier = modifier,
        bottomPadding = 16.dp
    )
}

@Composable
private fun SystemBarColors() {
    val systemUiController = rememberSystemUiController()

    val statusBarColor = MaterialTheme.colors.surface
    LaunchedEffect(statusBarColor) {
        systemUiController.setStatusBarColor(statusBarColor)
    }

    val navigationBarColor = MaterialTheme.colors.surface
    LaunchedEffect(navigationBarColor) {
        systemUiController.setNavigationBarColor(navigationBarColor)
    }
}

@Preview(showSystemUi = true)
@Composable
fun RootUiPreview() {
    AppTheme {
        RootUi(FakeRootComponent())
    }
}

class FakeRootComponent : RootComponent {

    override val childStack =
        createFakeChildStack(RootComponent.Child.Pokemons(FakePokemonsComponent()))

    override val messageComponent = FakeMessageComponent()
}