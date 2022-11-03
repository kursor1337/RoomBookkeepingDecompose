package com.kursor.roombookkeepingmobileupstack.features.crypto.data

import com.kursor.roombookkeepingmobileupstack.features.crypto.domain.CryptoInfo
import com.kursor.roombookkeepingmobileupstack.features.crypto.domain.Currency
import me.aartikov.replica.keyed.KeyedReplica

interface CryptoInfoListRepository {

    val cryptoInfoListByVsCurrencyReplica: KeyedReplica<Currency, List<CryptoInfo>>

}