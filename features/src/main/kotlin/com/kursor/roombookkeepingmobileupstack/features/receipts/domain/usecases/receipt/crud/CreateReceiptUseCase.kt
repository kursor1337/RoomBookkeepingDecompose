package com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases.receipt.crud

import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Receipt
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.repositories.ReceiptRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateReceiptUseCase(
    private val receiptRepository: ReceiptRepository
) {

    suspend operator fun invoke(receipt: Receipt) = withContext(Dispatchers.IO) {
        receiptRepository.save(receipt)
    }

}