package com.kursor.crypto_decompose.features.crypto.ui.description

import com.kursor.crypto.model.entities.CryptoDescription
import me.aartikov.replica.single.Loadable

interface CryptoDescriptionComponent {

    val cryptoDescriptionState: Loadable<CryptoDescription>

    fun onBackButtonPressed()

    sealed interface Output {
        object BackButtonPressed : Output
    }

}