package com.kursor.crypto_decompose.features.root

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import com.kursor.crypto_decompose.core.ComponentFactory
import com.kursor.crypto_decompose.features.root.ui.RealRootComponent
import com.kursor.crypto_decompose.features.root.ui.RootComponent

fun ComponentFactory.createRootComponent(componentContext: ComponentContext): RootComponent {
    return RealRootComponent(componentContext, get())
}