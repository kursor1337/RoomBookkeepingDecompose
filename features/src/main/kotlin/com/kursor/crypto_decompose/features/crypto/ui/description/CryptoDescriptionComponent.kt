package com.kursor.crypto_decompose.features.crypto.ui.description

import com.kursor.crypto_decompose.features.crypto.domain.CryptoDescription
import me.aartikov.replica.single.Loadable

interface CryptoDescriptionComponent {

    val cryptoDescriptionState: Loadable<CryptoDescription>

    val cryptoAdditionalInfo: CryptoAdditionalInfo

    fun onBackButtonPressed()

    fun refresh()

    sealed interface Output {
        object BackButtonPressed : Output
    }

    class CryptoAdditionalInfo(
        val id: String,
        val name: String,
        val imageLink: String
    )

}