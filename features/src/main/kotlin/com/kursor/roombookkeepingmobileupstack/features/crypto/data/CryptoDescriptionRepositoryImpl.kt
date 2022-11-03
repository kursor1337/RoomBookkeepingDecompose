package com.kursor.roombookkeepingmobileupstack.features.crypto.data

import com.kursor.roombookkeepingmobileupstack.features.crypto.domain.CryptoDescription
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.single.ReplicaSettings
import kotlin.time.Duration.Companion.seconds

class CryptoDescriptionRepositoryImpl(
    val replicaClient: ReplicaClient,
    val cryptoApi: CryptoApi
) : CryptoDescriptionRepository {

    override val cryptoDescriptionByIdReplica: KeyedReplica<String, CryptoDescription> =
        replicaClient.createKeyedReplica(
            name = "cryptoDescriptionById",
            childName = { cryptoId -> "cryptoId = $cryptoId" },
            childSettings = {
                ReplicaSettings(
                    staleTime = 10.seconds
                )
            }
        ) { cryptoId ->
            cryptoApi.getCryptoCurrencyDescription(cryptoId)
        }

}