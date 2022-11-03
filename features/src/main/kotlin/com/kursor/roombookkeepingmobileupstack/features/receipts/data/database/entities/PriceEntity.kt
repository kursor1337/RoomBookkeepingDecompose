package com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.entities

import androidx.room.Entity

@Entity
data class PriceEntity(
    val name: String,
    val value: Int,
    val personIds: List<Long>
) {
}