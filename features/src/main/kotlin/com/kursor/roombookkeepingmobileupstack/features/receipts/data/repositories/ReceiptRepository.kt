package com.kursor.roombookkeepingmobileupstack.features.receipts.data.repositories

import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Receipt

interface ReceiptRepository {

    suspend fun get(id: Long): Receipt?

    suspend fun getAll(): List<Receipt>

    suspend fun save(receipt: Receipt)

    suspend fun edit(receipt: Receipt)

    suspend fun delete(receipt: Receipt)
}