package com.kursor.crypto_decompose.features.crypto.ui.details

import com.kursor.crypto.model.entities.CryptoInfo
import com.kursor.crypto_decompose.features.crypto.domain.Currency
import me.aartikov.replica.single.Loadable

interface CryptoInfoListComponent {

    val cryptoInfoListState: Loadable<List<CryptoInfo>>

    val selectedCurrency: Currency

    fun onCurrencyClick(currency: Currency)

    fun onCryptoClick(cryptoId: String)

    fun onRetryClick()

    fun onRefresh()

    sealed interface Output {
        data class CryptoDescriptionRequested(val cryptoId: String) : Output
    }

}