package com.kursor.crypto_decompose.features.crypto.data

import com.kursor.crypto.model.entities.CryptoInfo
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.single.Fetcher
import me.aartikov.replica.single.Replica
import me.aartikov.replica.single.ReplicaSettings
import kotlin.time.Duration.Companion.seconds

class CryptoInfoListRepositoryImpl(
    val replicaClient: ReplicaClient,
    val cryptoApi: CryptoApi
) : CryptoInfoListRepository {

    override val cryptoInfoListReplica: Replica<List<CryptoInfo>> =
        replicaClient.createReplica(
            name = "cryptoInfoList",
            settings = ReplicaSettings(
                staleTime = 10.seconds,
                clearTime = 60.seconds
            ),
        ) {
            cryptoApi.getCryptoCurrencyInfoList("usd")
        }

}