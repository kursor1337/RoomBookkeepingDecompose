package com.kursor.crypto_decompose.features.crypto.ui.list

import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.kursor.crypto_decompose.features.crypto.domain.CryptoInfo
import com.kursor.crypto_decompose.core.error_handling.ErrorHandler
import com.kursor.crypto_decompose.core.utils.observe
import com.kursor.crypto_decompose.core.utils.persistent
import com.kursor.crypto_decompose.features.crypto.domain.Currency
import kotlinx.parcelize.Parcelize
import me.aartikov.replica.keyed.KeyedReplica

class RealCryptoInfoListComponent(
    componentContext: ComponentContext,
    val onOutput: (CryptoInfoListComponent.Output) -> Unit,
    private val cryptoInfoListReplica: KeyedReplica<Currency, List<CryptoInfo>>,
    errorHandler: ErrorHandler
) : ComponentContext by componentContext, CryptoInfoListComponent {

    override val cryptoInfoListState by cryptoInfoListReplica
        .observe(
            lifecycle,
            errorHandler,
            key = { selectedCurrency }
        )

    override var selectedCurrency by mutableStateOf(Currency.USD)
        private set

    init {
        persistent(
            save = { PersistentState(selectedCurrency) },
            restore = { state -> selectedCurrency = state.selectedCurrency }
        )
    }

    override fun onCurrencyClick(currency: Currency) {
        selectedCurrency = currency
    }

    override fun onCryptoClick(cryptoInfo: CryptoInfo) {
        onOutput(CryptoInfoListComponent.Output.CryptoDescriptionRequested(cryptoInfo))
    }

    override fun onRetryClick() {
        cryptoInfoListReplica.refresh(selectedCurrency)
    }

    override fun onRefresh() {
        cryptoInfoListReplica.refresh(selectedCurrency)
    }

    @Parcelize
    private data class PersistentState(
        val selectedCurrency: Currency
    ) : Parcelable

}