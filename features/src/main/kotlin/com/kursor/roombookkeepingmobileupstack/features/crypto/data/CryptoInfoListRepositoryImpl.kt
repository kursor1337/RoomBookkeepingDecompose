package com.kursor.roombookkeepingmobileupstack.features.crypto.data

import com.kursor.roombookkeepingmobileupstack.features.crypto.domain.CryptoInfo
import com.kursor.roombookkeepingmobileupstack.features.crypto.domain.Currency
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.single.ReplicaSettings
import kotlin.time.Duration.Companion.seconds

class CryptoInfoListRepositoryImpl(
    val replicaClient: ReplicaClient,
    val cryptoApi: CryptoApi
) : CryptoInfoListRepository {

    override val cryptoInfoListByVsCurrencyReplica: KeyedReplica<Currency, List<CryptoInfo>> =
        replicaClient.createKeyedReplica(
            name = "cryptoInfoListByVsCurrency",
            childName = { currency -> "currency = $currency" },
            childSettings = {
                ReplicaSettings(
                    staleTime = 10.seconds,
                    clearTime = 60.seconds
                )
            }
        ) {
            cryptoApi.getCryptoCurrencyInfoList(it.id)
        }

}