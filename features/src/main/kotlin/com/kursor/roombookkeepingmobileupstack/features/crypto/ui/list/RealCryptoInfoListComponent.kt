package com.kursor.roombookkeepingmobileupstack.features.crypto.ui.list

import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.kursor.roombookkeepingmobileupstack.features.crypto.domain.CryptoInfo
import com.kursor.roombookkeepingmobileupstack.core.error_handling.ErrorHandler
import com.kursor.roombookkeepingmobileupstack.core.utils.observe
import com.kursor.roombookkeepingmobileupstack.core.utils.persistent
import com.kursor.roombookkeepingmobileupstack.features.crypto.domain.Currency
import kotlinx.parcelize.Parcelize
import me.aartikov.replica.keyed.KeyedReplica

class RealCryptoInfoListComponent(
    componentContext: ComponentContext,
    val onOutput: (CryptoInfoListComponent.Output) -> Unit,
    private val cryptoInfoListReplica: KeyedReplica<Currency, List<CryptoInfo>>,
    errorHandler: ErrorHandler
) : ComponentContext by componentContext, CryptoInfoListComponent {


    override var selectedCurrency by mutableStateOf(Currency.USD)
        private set

    override val cryptoInfoListState by cryptoInfoListReplica
        .observe(
            lifecycle,
            errorHandler,
            key = { selectedCurrency }
        )

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