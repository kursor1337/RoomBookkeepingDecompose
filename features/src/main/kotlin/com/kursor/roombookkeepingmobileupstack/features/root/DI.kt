package com.kursor.roombookkeepingmobileupstack.features.root

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import com.kursor.roombookkeepingmobileupstack.core.ComponentFactory
import com.kursor.roombookkeepingmobileupstack.features.root.ui.RealRootComponent
import com.kursor.roombookkeepingmobileupstack.features.root.ui.RootComponent

fun ComponentFactory.createRootComponent(componentContext: ComponentContext): RootComponent {
    return RealRootComponent(componentContext, get())
}