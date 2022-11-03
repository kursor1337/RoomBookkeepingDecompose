package com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.typeConverters

import androidx.room.TypeConverter
import com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.entities.PersonEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PersonListTypeConverter {


    @TypeConverter
    fun fromListToString(personList: List<PersonEntity>): String = Json.encodeToString(personList)

    @TypeConverter
    fun fromStringToList(string: String): List<PersonEntity> = Json.decodeFromString(string)

}