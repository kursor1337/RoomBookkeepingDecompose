package com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases.receipt.crud

import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Receipt
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.repositories.ReceiptRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetReceiptUseCase(
    private val receiptRepository: ReceiptRepository
) {

    suspend operator fun invoke(id: Long): Receipt? = withContext(Dispatchers.IO) {
        receiptRepository.get(id)
    }

}