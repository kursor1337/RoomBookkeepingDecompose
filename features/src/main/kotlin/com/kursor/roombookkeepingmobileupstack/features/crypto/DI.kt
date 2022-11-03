package com.kursor.roombookkeepingmobileupstack.features.crypto

import com.arkivanov.decompose.ComponentContext
import com.kursor.roombookkeepingmobileupstack.core.ComponentFactory
import com.kursor.roombookkeepingmobileupstack.core.network.NetworkApiFactory
import com.kursor.roombookkeepingmobileupstack.features.crypto.data.*
import com.kursor.roombookkeepingmobileupstack.features.crypto.ui.CryptoComponent
import com.kursor.roombookkeepingmobileupstack.features.crypto.ui.RealCryptoComponent
import com.kursor.roombookkeepingmobileupstack.features.crypto.ui.description.CryptoDescriptionComponent
import com.kursor.roombookkeepingmobileupstack.features.crypto.ui.description.RealCryptoDescriptionComponent
import com.kursor.roombookkeepingmobileupstack.features.crypto.ui.list.CryptoInfoListComponent
import com.kursor.roombookkeepingmobileupstack.features.crypto.ui.list.RealCryptoInfoListComponent
import me.aartikov.replica.algebra.withKey
import org.koin.core.component.get
import org.koin.dsl.module

val cryptoModule = module {
    single<CryptoApi> {
        NetworkApiFactory(
            baseUrl = "https://api.coingecko.com/api/v3/",
            debugTools = get()
        ).createUnauthorizedApi()
    }

    single<CryptoInfoListRepository> {
        CryptoInfoListRepositoryImpl(
            replicaClient = get(),
            cryptoApi = get()
        )
    }

    single<CryptoDescriptionRepository> {
        CryptoDescriptionRepositoryImpl(
            replicaClient = get(),
            cryptoApi = get()
        )
    }
}

fun ComponentFactory.createCryptoInfoListComponent(
    componentContext: ComponentContext,
    onOutput: (CryptoInfoListComponent.Output) -> Unit
): CryptoInfoListComponent {
    val cryptoInfoListReplica = get<CryptoInfoListRepository>().cryptoInfoListByVsCurrencyReplica
    return RealCryptoInfoListComponent(
        componentContext,
        onOutput,
        cryptoInfoListReplica,
        errorHandler = get()
    )
}

fun ComponentFactory.createCryptoDescriptionComponent(
    componentContext: ComponentContext,
    cryptoAdditionalInfo: CryptoDescriptionComponent.CryptoAdditionalInfo,
    onOutput: (CryptoDescriptionComponent.Output) -> Unit
): CryptoDescriptionComponent {
    val cryptoDescriptionReplica = get<CryptoDescriptionRepository>()
        .cryptoDescriptionByIdReplica
        .withKey(cryptoAdditionalInfo.id)

    return RealCryptoDescriptionComponent(
        componentContext,
        onOutput,
        cryptoDescriptionReplica,
        cryptoAdditionalInfo,
        errorHandler = get()
    )
}

fun ComponentFactory.createCryptoComponent(
    componentContext: ComponentContext
): CryptoComponent {
    return RealCryptoComponent(
        componentContext,
        componentFactory = get()
    )
}