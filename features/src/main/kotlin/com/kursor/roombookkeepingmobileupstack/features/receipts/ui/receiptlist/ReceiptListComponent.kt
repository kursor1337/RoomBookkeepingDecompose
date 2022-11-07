package com.kursor.roombookkeepingmobileupstack.features.receipts.ui.receiptlist

import androidx.compose.runtime.State
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Receipt
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.ReceiptId

interface ReceiptListComponent {

    val receiptListState: List<Receipt>

    val selectedReceiptsState: List<Receipt>

    fun changeSelectionForReceipt(receipt: Receipt)

    fun deleteSelectedReceipts()

    fun onAddReceiptButtonClick()

    fun onReceiptClick(receiptId: ReceiptId)

    fun onPersonButtonClicked()

    sealed interface Output {

        object ReciptAddRequested : Output

        class ReceiptEditingRequested(val receiptId: ReceiptId) : Output

        object PersonsEditingRequested : Output

    }

}