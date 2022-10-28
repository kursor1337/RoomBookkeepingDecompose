package com.kursor.crypto_decompose.features.crypto.ui.description

import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backpressed.BackPressedHandler
import com.kursor.crypto_decompose.features.crypto.domain.CryptoDescription
import com.kursor.crypto_decompose.core.error_handling.ErrorHandler
import com.kursor.crypto_decompose.core.utils.observe
import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.single.Replica

class RealCryptoDescriptionComponent(
    componentContext: ComponentContext,
    private val onOutput: (CryptoDescriptionComponent.Output) -> Unit,
    private val cryptoDescriptionByIdReplica: Replica<CryptoDescription>,
    override val cryptoAdditionalInfo: CryptoDescriptionComponent.CryptoAdditionalInfo,
    errorHandler: ErrorHandler,
) : ComponentContext by componentContext, CryptoDescriptionComponent {

    override val cryptoDescriptionState by cryptoDescriptionByIdReplica
        .observe(
            lifecycle,
            errorHandler
        )

    override fun onBackButtonPressed() {
        onOutput(CryptoDescriptionComponent.Output.BackButtonPressed)
    }

    override fun refresh() {
        cryptoDescriptionByIdReplica.refresh()
    }

}