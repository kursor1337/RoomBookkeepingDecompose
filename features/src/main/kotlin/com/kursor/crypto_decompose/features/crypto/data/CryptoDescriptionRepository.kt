package com.kursor.crypto_decompose.features.crypto.data

import com.kursor.crypto_decompose.features.crypto.domain.CryptoDescription
import me.aartikov.replica.keyed.KeyedReplica

interface CryptoDescriptionRepository {

    val cryptoDescriptionByIdReplica: KeyedReplica<String, CryptoDescription>

}