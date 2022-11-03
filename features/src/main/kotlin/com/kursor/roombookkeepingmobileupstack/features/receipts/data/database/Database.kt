package com.kursor.roombookkeepingmobileupstack.features.receipts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.daos.PersonDao
import com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.daos.ReceiptDao
import com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.entities.PersonEntity
import com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.entities.ReceiptEntity
import com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.typeConverters.DateTypeConverter
import com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.typeConverters.PersonListTypeConverter
import com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.typeConverters.PriceListTypeConverter

@Database(entities = [PersonEntity::class, ReceiptEntity::class], version = 1)
@TypeConverters(
    PersonListTypeConverter::class,
    DateTypeConverter::class,
    PriceListTypeConverter::class
)
abstract class Database : RoomDatabase() {

    abstract fun personDao(): PersonDao

    abstract fun receiptDao(): ReceiptDao

}