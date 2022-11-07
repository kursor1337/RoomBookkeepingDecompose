package com.kursor.roombookkeepingmobileupstack.features.receipts.ui.receipt

import com.arkivanov.decompose.ComponentContext
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.ReceiptId
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases.CalculateOutcomesUseCase
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases.receipt.crud.CreateReceiptUseCase
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases.receipt.crud.EditReceiptUseCase
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases.receipt.crud.GetReceiptUseCase

class RealAddReceiptComponent(
    componentContext: ComponentContext,
    createReceiptUseCase: CreateReceiptUseCase,
    editReceiptUseCase: EditReceiptUseCase,
    getReceiptUseCase: GetReceiptUseCase,
    calculateOutcomesUseCase: CalculateOutcomesUseCase
): RealEditReceiptComponent(
    componentContext,
    receiptId = ReceiptId(System.currentTimeMillis()),
    editReceiptUseCase,
    getReceiptUseCase,
    calculateOutcomesUseCase
) {

}