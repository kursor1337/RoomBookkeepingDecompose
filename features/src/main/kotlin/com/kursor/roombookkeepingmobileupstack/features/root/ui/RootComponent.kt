package com.kursor.roombookkeepingmobileupstack.features.root.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.kursor.roombookkeepingmobileupstack.core.message.ui.MessageComponent
import com.kursor.roombookkeepingmobileupstack.features.crypto.ui.CryptoComponent
import com.kursor.roombookkeepingmobileupstack.features.pokemons.ui.PokemonsComponent

/**
 * A root of a Decompose component tree.
 *
 * Note: Try to minimize child count in RootComponent. It should operate by flows of screens rather than separate screens.
 */
interface RootComponent {

    val childStack: ChildStack<*, Child>

    val messageComponent: MessageComponent

    sealed interface Child {

        class Pokemons(val component: PokemonsComponent) : Child

        class Crypto(val component: CryptoComponent) : Child

    }
}