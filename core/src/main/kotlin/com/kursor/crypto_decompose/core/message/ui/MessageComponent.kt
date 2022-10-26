package com.kursor.crypto_decompose.core.message.ui

import com.kursor.crypto_decompose.core.message.domain.Message

/**
 * A component for centralized message showing. There should be only one instance
 * of this component in the app connected to the root component.
 */
interface MessageComponent {

    val visibleMessage: Message?

    fun onActionClick()
}