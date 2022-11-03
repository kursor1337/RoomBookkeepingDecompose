package com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases.price

import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Person
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Price
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Receipt
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.repositories.ReceiptRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeletePersonFromPriceUseCase(
    private val receiptRepository: ReceiptRepository
) {

    suspend operator fun invoke(receipt: Receipt, price: Price, person: Person) {
        withContext(Dispatchers.IO) {
            receiptRepository.edit(
                Receipt(
                    id = receipt.id,
                    name = receipt.name,
                    dateTime = receipt.dateTime,
                    priceList = receipt.priceList.toMutableList().apply {
                        val index = this.indexOf(price)
                        set(index, Price(
                            name = price.name,
                            value = price.value,
                            persons = price.persons.toMutableList().apply {
                                remove(person)
                            }
                        )
                        )
                    }
                )
            )
        }
    }

}