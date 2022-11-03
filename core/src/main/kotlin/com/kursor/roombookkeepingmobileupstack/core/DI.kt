package com.kursor.roombookkeepingmobileupstack.core

import com.arkivanov.decompose.ComponentContext
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.network.AndroidNetworkConnectivityProvider
import me.aartikov.replica.network.NetworkConnectivityProvider
import org.koin.core.component.get
import org.koin.dsl.module
import com.kursor.roombookkeepingmobileupstack.core.activity.ActivityProvider
import com.kursor.roombookkeepingmobileupstack.core.debug_tools.DebugTools
import com.kursor.roombookkeepingmobileupstack.core.debug_tools.RealDebugTools
import com.kursor.roombookkeepingmobileupstack.core.error_handling.ErrorHandler
import com.kursor.roombookkeepingmobileupstack.core.message.data.MessageService
import com.kursor.roombookkeepingmobileupstack.core.message.data.MessageServiceImpl
import com.kursor.roombookkeepingmobileupstack.core.message.ui.MessageComponent
import com.kursor.roombookkeepingmobileupstack.core.message.ui.RealMessageComponent
import com.kursor.roombookkeepingmobileupstack.core.network.NetworkApiFactory
import com.kursor.roombookkeepingmobileupstack.core.permissions.PermissionManager

fun coreModule(backendUrl: String) = module {
    single { ActivityProvider() }
    single<NetworkConnectivityProvider> { AndroidNetworkConnectivityProvider(get()) }
    single { ReplicaClient(get()) }
    single<MessageService> { MessageServiceImpl() }
    single { ErrorHandler(get()) }
    single<DebugTools> { RealDebugTools(get(), get()) }
    single { NetworkApiFactory(backendUrl, get()) }
    single { PermissionManager(get(), get()) }
}

fun ComponentFactory.createMessageComponent(
    componentContext: ComponentContext
): MessageComponent {
    return RealMessageComponent(componentContext, get())
}