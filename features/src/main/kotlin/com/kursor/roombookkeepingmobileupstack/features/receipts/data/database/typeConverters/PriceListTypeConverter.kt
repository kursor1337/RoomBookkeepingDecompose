package com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.typeConverters

import androidx.room.TypeConverter
import com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.entities.PriceEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.lang.reflect.Type

class PriceListTypeConverter {

    @TypeConverter
    fun fromPriceToString(priceList: List<PriceEntity>): String = Json.encodeToString(priceList)

    @TypeConverter
    fun fromStringToList(string: String): List<PriceEntity> = Json.decodeFromString(string)

}