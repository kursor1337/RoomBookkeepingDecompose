package com.kursor.roombookkeepingmobileupstack.core.message.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnCreate
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.kursor.roombookkeepingmobileupstack.core.message.data.MessageService
import com.kursor.roombookkeepingmobileupstack.core.message.domain.Message
import com.kursor.roombookkeepingmobileupstack.core.utils.componentCoroutineScope

class RealMessageComponent(
    componentContext: ComponentContext,
    private val messageService: MessageService,
) : ComponentContext by componentContext, MessageComponent {

    companion object {
        private const val SHOW_TIME = 4000L
    }

    private val coroutineScope = componentCoroutineScope()

    override var visibleMessage by mutableStateOf<Message?>(null)
        private set

    private var autoDismissJob: Job? = null

    init {
        lifecycle.doOnCreate(::collectMessages)
    }

    override fun onActionClick() {
        autoDismissJob?.cancel()
        visibleMessage?.action?.invoke()
        visibleMessage = null
    }

    private fun collectMessages() {
        coroutineScope.launch {
            messageService.messageFlow.collect { messageData ->
                showMessage(messageData)
            }
        }
    }

    private fun showMessage(message: Message) {
        autoDismissJob?.cancel()
        visibleMessage = message
        autoDismissJob = coroutineScope.launch {
            delay(SHOW_TIME)
            visibleMessage = null
        }
    }
}