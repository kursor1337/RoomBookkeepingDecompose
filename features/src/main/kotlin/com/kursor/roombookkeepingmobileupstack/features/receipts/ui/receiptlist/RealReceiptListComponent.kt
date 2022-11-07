package com.kursor.roombookkeepingmobileupstack.features.receipts.ui.receiptlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Receipt
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.ReceiptId
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

    private fun selectReceipt(receipt: Receipt) {
        selectedReceiptsState += receipt
    }

    private fun unselectReceipt(receipt: Receipt) {
        selectedReceiptsState -= receipt
    }

    override fun changeSelectionForReceipt(receipt: Receipt) {
        if (receipt in selectedReceiptsState) {
            unselectReceipt(receipt)
        } else selectReceipt(receipt)
    }

    override fun deleteSelectedReceipts() {
        coroutineScope.launch {
            selectedReceiptsState.forEach { deleteReceiptUseCase(it) }
        }
    }

    override fun onAddReceiptButtonClick() {
        onOutput(ReceiptListComponent.Output.ReciptAddRequested)
    }

    override fun onReceiptClick(receiptId: ReceiptId) {
        onOutput(ReceiptListComponent.Output.ReceiptEditingRequested(receiptId))
    }

    override fun onPersonButtonClicked() {
        onOutput(ReceiptListComponent.Output.PersonsEditingRequested)
    }
}