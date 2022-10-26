package com.kursor.crypto_decompose.core

import com.arkivanov.decompose.ComponentContext
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.network.AndroidNetworkConnectivityProvider
import me.aartikov.replica.network.NetworkConnectivityProvider
import org.koin.core.component.get
import org.koin.dsl.module
import com.kursor.crypto_decompose.core.activity.ActivityProvider
import com.kursor.crypto_decompose.core.debug_tools.DebugTools
import com.kursor.crypto_decompose.core.debug_tools.RealDebugTools
import com.kursor.crypto_decompose.core.error_handling.ErrorHandler
import com.kursor.crypto_decompose.core.message.data.MessageService
import com.kursor.crypto_decompose.core.message.data.MessageServiceImpl
import com.kursor.crypto_decompose.core.message.ui.MessageComponent
import com.kursor.crypto_decompose.core.message.ui.RealMessageComponent
import com.kursor.crypto_decompose.core.network.NetworkApiFactory
import com.kursor.crypto_decompose.core.permissions.PermissionManager

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