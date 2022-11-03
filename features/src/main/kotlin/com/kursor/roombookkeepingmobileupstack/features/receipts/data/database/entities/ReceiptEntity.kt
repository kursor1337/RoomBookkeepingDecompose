package com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ReceiptEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo val name: String,
    @ColumnInfo val dateTime: Date,
    @ColumnInfo val priceList: List<PriceEntity>
)