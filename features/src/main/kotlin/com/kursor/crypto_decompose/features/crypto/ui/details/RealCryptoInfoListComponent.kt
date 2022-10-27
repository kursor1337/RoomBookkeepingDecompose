package com.kursor.crypto_decompose.features.crypto.ui.details

import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.kursor.crypto.model.entities.CryptoInfo
import com.kursor.crypto_decompose.core.error_handling.ErrorHandler
import com.kursor.crypto_decompose.core.utils.observe
import com.kursor.crypto_decompose.core.utils.persistent
import com.kursor.crypto_decompose.features.crypto.domain.Currency
import com.kursor.crypto_decompose.features.pokemons.domain.PokemonTypeId
import kotlinx.parcelize.Parcelize
import me.aartikov.replica.single.Replica

class RealCryptoInfoListComponent(
    componentContext: ComponentContext,
    val onOutput: (CryptoInfoListComponent.Output) -> Unit,
    val cryptoInfoListReplica: Replica<List<CryptoInfo>>,
    errorHandler: ErrorHandler
) : ComponentContext by componentContext, CryptoInfoListComponent {

    override val cryptoInfoListState by cryptoInfoListReplica.observe(lifecycle, errorHandler)

    override var selectedCurrency by mutableStateOf(Currency.USD)
        private set

    init {
        persistent(
            save = { PersistentState(selectedCurrency) },
            restore = { state -> selectedCurrency = state.selectedCurrency }
        )
    }

    override fun onCurrencyClick(currency: Currency) {
        TODO("Not yet implemented")
    }

    override fun onCryptoClick(cryptoId: String) {
        TODO("Not yet implemented")
    }

    override fun onRetryClick() {
        TODO("Not yet implemented")
    }

    override fun onRefresh() {
        TODO("Not yet implemented")
    }

    @Parcelize
    private data class PersistentState(
        val selectedCurrency: Currency
    ) : Parcelable

}