package com.kursor.roombookkeepingmobileupstack.features.receipts.ui.receiptlist

import androidx.compose.runtime.State
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Receipt
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.ReceiptId

interface ReceiptListComponent {

    val receiptListState: List<Receipt>

    val selectedReceiptsState: List<Receipt>

    fun selectReceipt(receipt: Receipt)

    fun unselectReceipt(receipt: Receipt)

    fun changeSelectionForReceipt(receipt: Receipt) {
        if (receipt in selectedReceiptsState) {
            unselectReceipt(receipt)
        } else selectReceipt(receipt)
    }

    fun deleteSelectedReceipts()

    sealed interface Output {

        class ReceiptEditingRequested(val receiptId: ReceiptId) : Output

        object PersonsEditingRequested : Output

    }

}