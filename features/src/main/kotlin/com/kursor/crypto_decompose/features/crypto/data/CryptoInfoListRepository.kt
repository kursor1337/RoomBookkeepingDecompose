package com.kursor.crypto_decompose.features.crypto.data

import com.kursor.crypto_decompose.features.crypto.domain.CryptoInfo
import com.kursor.crypto_decompose.features.crypto.domain.Currency
import me.aartikov.replica.keyed.KeyedReplica

interface CryptoInfoListRepository {

    val cryptoInfoListByVsCurrencyReplica: KeyedReplica<Currency, List<CryptoInfo>>

}