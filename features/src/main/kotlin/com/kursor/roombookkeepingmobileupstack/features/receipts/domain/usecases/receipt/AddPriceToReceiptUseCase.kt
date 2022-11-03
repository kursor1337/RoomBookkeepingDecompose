package com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases.receipt

import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Price
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Receipt
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.repositories.ReceiptRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddPriceToReceiptUseCase(
    private val receiptRepository: ReceiptRepository
) {

    suspend operator fun invoke(receipt: Receipt, price: Price) {
        withContext(Dispatchers.IO) {
            receiptRepository.edit(
                Receipt(
                    id = receipt.id,
                    name = receipt.name,
                    dateTime = receipt.dateTime,
                    priceList = receipt.priceList.toMutableList().apply {
                        add(price)
                    }
                )
            )
        }

    }

}