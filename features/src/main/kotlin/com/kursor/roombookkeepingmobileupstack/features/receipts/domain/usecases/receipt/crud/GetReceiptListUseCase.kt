package com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases.receipt.crud

import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Receipt
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.repositories.ReceiptRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetReceiptListUseCase(
    private val receiptRepository: ReceiptRepository
) {

    suspend operator fun invoke(): List<Receipt> = withContext(Dispatchers.IO) {
        receiptRepository.getAll()
    }


}