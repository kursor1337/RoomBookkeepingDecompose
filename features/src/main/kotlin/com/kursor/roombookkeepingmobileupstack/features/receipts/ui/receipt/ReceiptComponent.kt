package com.kursor.roombookkeepingmobileupstack.features.receipts.ui.receipt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Person
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Price
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Receipt
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.ReceiptId
import com.kursor.roombookkeepingmobileupstack.features.receipts.ui.receiptlist.ReceiptListComponent
import kotlinx.coroutines.launch
import java.util.*

interface ReceiptComponent {

    val priceListState: List<Price>

    val nameState: String

    val outcomesState: Map<Person, Double>

    var receipt: Receipt

    fun changeName(name: String)

    fun deletePrice(price: Price)

    fun submit()

    sealed interface Output {

        class PriceEditingRequested(receiptId: ReceiptId, price: Price) : Output

        class PriceAddRequested(receiptId: ReceiptId) : Output

    }

}