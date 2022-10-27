package com.kursor.crypto_decompose.features.crypto.data

import com.kursor.crypto.model.entities.CryptoDescription
import me.aartikov.replica.keyed.KeyedReplica

interface CryptoDescriptionRepository {

    val cryptoDescriptionByIdReplica: KeyedReplica<String, CryptoDescription>

}