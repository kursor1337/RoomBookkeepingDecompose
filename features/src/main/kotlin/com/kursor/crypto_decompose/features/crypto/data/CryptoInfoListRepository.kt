package com.kursor.crypto_decompose.features.crypto.data

import com.kursor.crypto.model.entities.CryptoDescription
import com.kursor.crypto.model.entities.CryptoInfo
import me.aartikov.replica.single.Replica

interface CryptoInfoListRepository {

    val cryptoInfoListReplica: Replica<List<CryptoInfo>>

}