package com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersonEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo val name: String
)