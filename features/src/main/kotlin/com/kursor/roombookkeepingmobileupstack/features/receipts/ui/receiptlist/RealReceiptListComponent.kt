package com.kursor.roombookkeepingmobileupstack.features.receipts.ui.receiptlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Receipt
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases.receipt.crud.DeleteReceiptUseCase
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases.receipt.crud.GetReceiptListUseCase
import kotlinx.coroutines.launch
import me.aartikov.replica.decompose.coroutineScope

class RealReceiptListComponent(
    componentContext: ComponentContext,
    private val onOutput: (ReceiptListComponent.Output) -> Unit,
    private val getReceiptListUseCase: GetReceiptListUseCase,
    private val deleteReceiptUseCase: DeleteReceiptUseCase
): ReceiptListComponent, ComponentContext by componentContext {

    override var receiptListState: List<Receipt> by mutableStateOf(emptyList())

    override val selectedReceiptsState: MutableList<Receipt> = mutableStateListOf()

    private val coroutineScope = lifecycle.coroutineScope()

    init {
        coroutineScope.launch {
            receiptListState = getReceiptListUseCase()
        }
    }

    override fun selectReceipt(receipt: Receipt) {
        selectedReceiptsState += receipt
    }

    override fun unselectReceipt(receipt: Receipt) {
        selectedReceiptsState -= receipt
    }

    override fun deleteSelectedReceipts() {
        coroutineScope.launch {
            selectedReceiptsState.forEach { deleteReceiptUseCase(it) }
        }
    }


}