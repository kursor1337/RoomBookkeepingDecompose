package com.kursor.roombookkeepingmobileupstack.features.crypto.ui.description

import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.kursor.roombookkeepingmobileupstack.features.crypto.domain.CryptoDescription
import com.kursor.roombookkeepingmobileupstack.core.error_handling.ErrorHandler
import com.kursor.roombookkeepingmobileupstack.core.utils.observe
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