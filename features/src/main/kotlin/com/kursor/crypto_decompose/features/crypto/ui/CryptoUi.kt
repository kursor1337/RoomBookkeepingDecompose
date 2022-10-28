package com.kursor.crypto_decompose.features.crypto.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.kursor.crypto_decompose.features.crypto.ui.description.CryptoDescriptionUi
import com.kursor.crypto_decompose.features.crypto.ui.list.CryptoInfoListUi

@Composable
fun CryptoUi(
    cryptoComponent: CryptoComponent,
    modifier: Modifier
) {

    Children(stack = cryptoComponent.childStack, modifier) { child ->
        when (val instance = child.instance) {
            is CryptoComponent.Child.List -> CryptoInfoListUi(instance.component)
            is CryptoComponent.Child.Description -> CryptoDescriptionUi(instance.component)
        }
    }

}