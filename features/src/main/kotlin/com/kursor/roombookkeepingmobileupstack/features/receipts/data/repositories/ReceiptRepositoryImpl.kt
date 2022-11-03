package com.kursor.roombookkeepingmobileupstack.features.receipts.data.repositories

import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Price
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Receipt
import com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.daos.PersonDao
import com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.daos.ReceiptDao
import com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.entities.PriceEntity
import com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.entities.ReceiptEntity
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.ReceiptId
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.repositories.ReceiptRepository

class ReceiptRepositoryImpl(
    val receiptDao: ReceiptDao,
    val personDao: PersonDao
) : ReceiptRepository {

    override suspend fun get(id: Long): Receipt? = receiptDao.get(id)?.convertToModelEntity()

    override suspend fun getAll(): List<Receipt> =
        receiptDao.getAll().map { it.convertToModelEntity() }

    override suspend fun save(receipt: Receipt) =
        receiptDao.insert(receipt.convertToDatabaseEntity())

    override suspend fun edit(receipt: Receipt) =
        receiptDao.update(receipt.convertToDatabaseEntity())

    override suspend fun delete(receipt: Receipt) =
        receiptDao.delete(receipt.convertToDatabaseEntity())


    private suspend fun ReceiptEntity.convertToModelEntity(): Receipt = Receipt(
        id = ReceiptId(this.id),
        name = this.name,
        dateTime = this.dateTime,
        priceList = this.priceList.map { priceEntity ->
            Price(
                name = priceEntity.name,
                value = priceEntity.value,
                persons = priceEntity.personIds.map { personDao.get(it)!!.convertToModelEntity() }
            )
        }
    )

    private fun Receipt.convertToDatabaseEntity(): ReceiptEntity = ReceiptEntity(
        id = this.id.id,
        name = this.name,
        dateTime = this.dateTime,
        priceList = this.priceList.map { price ->
            PriceEntity(
                name = price.name,
                value = price.value,
                personIds = price.persons.map { person -> person.id.id }
            )
        }
    )
}

