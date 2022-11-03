package com.kursor.roombookkeepingmobileupstack.features.crypto.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.kursor.roombookkeepingmobileupstack.features.crypto.ui.description.CryptoDescriptionUi
import com.kursor.roombookkeepingmobileupstack.features.crypto.ui.list.CryptoInfoListUi

@Composable
fun CryptoUi(
    component: CryptoComponent,
    modifier: Modifier = Modifier
) {

    Children(stack = component.childStack, modifier) { child ->
        when (val instance = child.instance) {
            is CryptoComponent.Child.List -> CryptoInfoListUi(instance.component)
            is CryptoComponent.Child.Description -> CryptoDescriptionUi(instance.component)
        }
    }

}