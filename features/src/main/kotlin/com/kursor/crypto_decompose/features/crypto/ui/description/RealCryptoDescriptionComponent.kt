package com.kursor.crypto_decompose.features.crypto.ui.description

import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.kursor.crypto.model.entities.CryptoDescription
import com.kursor.crypto_decompose.core.error_handling.ErrorHandler
import com.kursor.crypto_decompose.core.utils.observe
import me.aartikov.replica.single.Replica

class RealCryptoDescriptionComponent(
    componentContext: ComponentContext,
    private val onOutput: (CryptoDescriptionComponent.Output) -> Unit,
    private val cryptoReplica: Replica<CryptoDescription>,
    errorHandler: ErrorHandler
) : ComponentContext by componentContext, CryptoDescriptionComponent {

    override val cryptoDescriptionState by cryptoReplica.observe(lifecycle, errorHandler)

    override fun onBackButtonPressed() {
        onOutput(CryptoDescriptionComponent.Output.BackButtonPressed)
    }

}