package com.kursor.roombookkeepingmobileupstack.features.root.ui

import android.os.Parcelable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.parcelize.Parcelize
import com.kursor.roombookkeepingmobileupstack.core.ComponentFactory
import com.kursor.roombookkeepingmobileupstack.core.createMessageComponent
import com.kursor.roombookkeepingmobileupstack.core.utils.toComposeState
import com.kursor.roombookkeepingmobileupstack.features.crypto.createCryptoComponent
import com.kursor.roombookkeepingmobileupstack.features.pokemons.createPokemonsComponent

class RealRootComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, RootComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: ChildStack<*, RootComponent.Child> by childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Crypto,
        handleBackButton = true,
        childFactory = ::createChild
    ).toComposeState(lifecycle)

    override val messageComponent = componentFactory.createMessageComponent(
        childContext(key = "message")
    )

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {
        is ChildConfig.Pokemons -> {
            RootComponent.Child.Pokemons(
                componentFactory.createPokemonsComponent(componentContext)
            )
        }

        is ChildConfig.Crypto -> {
            RootComponent.Child.Crypto(
                componentFactory.createCryptoComponent(componentContext)
            )
        }
    }

    private sealed interface ChildConfig : Parcelable {

        @Parcelize
        object Pokemons : ChildConfig

        @Parcelize
        object Crypto : ChildConfig

    }
}