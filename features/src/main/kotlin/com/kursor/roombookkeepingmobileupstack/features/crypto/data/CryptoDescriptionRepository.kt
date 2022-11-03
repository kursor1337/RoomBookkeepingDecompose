package com.kursor.roombookkeepingmobileupstack.features.crypto.data

import com.kursor.roombookkeepingmobileupstack.features.crypto.domain.CryptoDescription
import me.aartikov.replica.keyed.KeyedReplica

interface CryptoDescriptionRepository {

    val cryptoDescriptionByIdReplica: KeyedReplica<String, CryptoDescription>

}