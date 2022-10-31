package com.kursor.crypto_decompose.features.crypto.ui

import android.os.Parcelable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.kursor.crypto_decompose.core.ComponentFactory
import com.kursor.crypto_decompose.core.utils.toComposeState
import com.kursor.crypto_decompose.features.crypto.createCryptoDescriptionComponent
import com.kursor.crypto_decompose.features.crypto.createCryptoInfoListComponent
import com.kursor.crypto_decompose.features.crypto.ui.description.CryptoDescriptionComponent
import com.kursor.crypto_decompose.features.crypto.ui.list.CryptoInfoListComponent
import kotlinx.parcelize.Parcelize
import timber.log.Timber

class RealCryptoComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, CryptoComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: ChildStack<*, CryptoComponent.Child> by childStack(
        source = navigation,
        initialConfiguration = ChildConfig.List,
        handleBackButton = true,
        childFactory = ::createChild
    ).toComposeState(lifecycle)

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): CryptoComponent.Child = when (config) {
        is ChildConfig.List -> {
            CryptoComponent.Child.List(
                componentFactory.createCryptoInfoListComponent(
                    componentContext,
                    onOutput = ::onCryptoListOutput
                )
            )
        }

        is ChildConfig.Description -> {
            CryptoComponent.Child.Description(
                componentFactory.createCryptoDescriptionComponent(
                    componentContext,
                    cryptoAdditionalInfo = CryptoDescriptionComponent.CryptoAdditionalInfo(
                        config.args.cryptoId,
                        config.args.cryptoName,
                        config.args.cryptoImageLink
                    ),
                    onOutput = ::onCryptoDescriptionOutput
                )
            )
        }
    }

    private fun onCryptoListOutput(output: CryptoInfoListComponent.Output) {
        when (output) {
            is CryptoInfoListComponent.Output.CryptoDescriptionRequested -> {
                navigation.push(
                    ChildConfig.Description(
                        ChildConfig.Description.Args(
                            cryptoId = output.cryptoInfo.id,
                            cryptoName = output.cryptoInfo.name,
                            cryptoImageLink = output.cryptoInfo.image
                        )
                    )
                )
            }
        }
    }

    private fun onCryptoDescriptionOutput(output: CryptoDescriptionComponent.Output) {
        when (output) {
            is CryptoDescriptionComponent.Output.BackButtonPressed -> {
                navigation.pop()
            }
        }
    }

    private sealed interface ChildConfig : Parcelable {

        @Parcelize
        object List : ChildConfig

        @Parcelize
        data class Description(val args: Args) : ChildConfig {

            @Parcelize
            data class Args(
                val cryptoId: String,
                val cryptoName: String,
                val cryptoImageLink: String
            ) : Parcelable

        }

    }

}