package com.kursor.crypto_decompose.features.crypto.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.kursor.crypto_decompose.features.crypto.ui.list.CryptoInfoListComponent
import com.kursor.crypto_decompose.features.crypto.ui.description.CryptoDescriptionComponent

interface CryptoComponent {

    val childStack: ChildStack<*, Child>

    sealed interface Child {
        class List(val component: CryptoInfoListComponent) : Child
        class Description(val component: CryptoDescriptionComponent) : Child
    }

}