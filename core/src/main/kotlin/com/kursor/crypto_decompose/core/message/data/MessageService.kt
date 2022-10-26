package com.kursor.crypto_decompose.core.message.data

import kotlinx.coroutines.flow.Flow
import com.kursor.crypto_decompose.core.message.domain.Message

/**
 * A service for centralized message showing
 */
interface MessageService {

    val messageFlow: Flow<Message>

    fun showMessage(message: Message)
}