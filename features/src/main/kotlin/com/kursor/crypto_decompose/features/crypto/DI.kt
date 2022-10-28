package com.kursor.crypto_decompose.features.crypto

import com.arkivanov.decompose.ComponentContext
import com.kursor.crypto_decompose.core.ComponentFactory
import com.kursor.crypto_decompose.core.network.NetworkApiFactory
import com.kursor.crypto_decompose.features.crypto.data.*
import com.kursor.crypto_decompose.features.crypto.ui.CryptoComponent
import com.kursor.crypto_decompose.features.crypto.ui.RealCryptoComponent
import com.kursor.crypto_decompose.features.crypto.ui.description.CryptoDescriptionComponent
import com.kursor.crypto_decompose.features.crypto.ui.description.RealCryptoDescriptionComponent
import com.kursor.crypto_decompose.features.crypto.ui.list.CryptoInfoListComponent
import com.kursor.crypto_decompose.features.crypto.ui.list.RealCryptoInfoListComponent
import kotlinx.serialization.json.Json
import me.aartikov.replica.algebra.withKey
import okhttp3.MediaType.Companion.toMediaType
import org.koin.core.component.get
import org.koin.dsl.module
import retrofit2.Retrofit
import kotlin.math.sin

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
    cryptoId: String,
    onOutput: (CryptoDescriptionComponent.Output) -> Unit
): CryptoDescriptionComponent {
    val cryptoDescriptionReplica = get<CryptoDescriptionRepository>()
        .cryptoDescriptionByIdReplica
        .withKey(cryptoId)

    return RealCryptoDescriptionComponent(
        componentContext,
        onOutput,
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