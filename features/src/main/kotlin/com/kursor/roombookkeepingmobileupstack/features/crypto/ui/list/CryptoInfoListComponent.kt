package com.kursor.roombookkeepingmobileupstack.features.crypto.ui.list

import com.kursor.roombookkeepingmobileupstack.features.crypto.domain.CryptoInfo
import com.kursor.roombookkeepingmobileupstack.features.crypto.domain.Currency
import me.aartikov.replica.single.Loadable

interface CryptoInfoListComponent {

    val cryptoInfoListState: Loadable<List<CryptoInfo>>

    val selectedCurrency: Currency

    fun onCurrencyClick(currency: Currency)

    fun onCryptoClick(cryptoInfo: CryptoInfo)

    fun onRetryClick()

    fun onRefresh()

    sealed interface Output {
        data class CryptoDescriptionRequested(val cryptoInfo: CryptoInfo) : Output
    }

}