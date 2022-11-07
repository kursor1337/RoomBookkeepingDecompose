package com.kursor.roombookkeepingmobileupstack.features.receipts.ui.receipt

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Person
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Price
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Receipt
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.ReceiptId
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases.CalculateOutcomesUseCase
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases.receipt.crud.EditReceiptUseCase
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases.receipt.crud.GetReceiptUseCase
import kotlinx.coroutines.launch
import me.aartikov.replica.decompose.coroutineScope

open class RealEditReceiptComponent(
    val componentContext: ComponentContext,
    val receiptId: ReceiptId,
    val editReceiptUseCase: EditReceiptUseCase,
    val getReceiptUseCase: GetReceiptUseCase,
    val calculateOutcomesUseCase: CalculateOutcomesUseCase
) : ComponentContext by componentContext, ReceiptComponent {

    private val coroutineScope = lifecycle.coroutineScope()

    override val priceListState: MutableList<Price> = mutableStateListOf()

    override var nameState: String by mutableStateOf("")

    override var outcomesState: Map<Person, Double> by mutableStateOf(emptyMap())

    override lateinit var receipt: Receipt

    protected fun loadData() {
        coroutineScope.launch {
            receipt = getReceiptUseCase(receiptId.id)!!
            nameState = receipt.name
            priceListState.clear()
            priceListState += receipt.priceList
            outcomesState = calculateOutcomesUseCase(receipt)
        }
    }

    override fun changeName(name: String) {
        nameState = name
    }

    override fun deletePrice(price: Price) {
        coroutineScope.launch {
            priceListState.remove(price)
            outcomesState = calculateOutcomesUseCase(priceListState)
        }
    }

    override fun submit() {
        coroutineScope.launch {
            editReceiptUseCase(receipt)
        }
    }
}