package com.kursor.crypto_decompose.features.crypto

import com.arkivanov.decompose.ComponentContext
import com.kursor.crypto_decompose.core.ComponentFactory
import com.kursor.crypto_decompose.features.crypto.data.CryptoDescriptionRepository
import com.kursor.crypto_decompose.features.crypto.data.CryptoInfoListRepository
import com.kursor.crypto_decompose.features.crypto.data.CryptoInfoListRepositoryImpl
import com.kursor.crypto_decompose.features.crypto.ui.CryptoComponent
import com.kursor.crypto_decompose.features.crypto.ui.RealCryptoComponent
import com.kursor.crypto_decompose.features.crypto.ui.description.CryptoDescriptionComponent
import com.kursor.crypto_decompose.features.crypto.ui.description.RealCryptoDescriptionComponent
import com.kursor.crypto_decompose.features.crypto.ui.list.CryptoInfoListComponent
import com.kursor.crypto_decompose.features.crypto.ui.list.RealCryptoInfoListComponent
import me.aartikov.replica.algebra.withKey
import org.koin.core.component.get
import org.koin.dsl.module
import kotlin.math.sin

val cryptoModule = module {
    single<CryptoInfoListRepository> {
        CryptoInfoListRepositoryImpl(
            replicaClient = get(),
            cryptoApi
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
    cryptoId: String
): CryptoDescriptionComponent {
    val cryptoDescriptionReplica = get<CryptoDescriptionRepository>()
        .cryptoDescriptionByIdReplica
        .withKey(cryptoId)

    return RealCryptoDescriptionComponent(
        componentContext,
        cryptoDescriptionReplica,
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