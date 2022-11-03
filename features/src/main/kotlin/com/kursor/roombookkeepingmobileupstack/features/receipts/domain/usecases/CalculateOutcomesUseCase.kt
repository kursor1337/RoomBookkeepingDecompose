package com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases

import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Person
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Price
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Receipt
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.calculateCommonPersons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CalculateOutcomesUseCase {

    suspend operator fun invoke(receipt: Receipt): Map<Person, Double> {
        return withContext(Dispatchers.Default) {
            receipt.persons.associateWith { person ->
                var outcomeValue = 0.0
                receipt.priceList.forEach { price ->
                    if (person !in price.persons) return@forEach
                    outcomeValue += price.value.toDouble() / price.persons.size
                }
                outcomeValue
            }
        }
    }

    suspend operator fun invoke(priceList: List<Price>): Map<Person, Double> {
        return withContext(Dispatchers.Default) {
            priceList.calculateCommonPersons().associateWith { person ->
                var outcomeValue = 0.0
                priceList.forEach { price ->
                    if (person !in price.persons) return@forEach
                    outcomeValue += price.value.toDouble() / price.persons.size
                }
                outcomeValue
            }
        }
    }

}