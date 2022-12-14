package com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.daos

import androidx.room.*
import com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.entities.PersonEntity

@Dao
interface PersonDao {

    @Query("SELECT * FROM PersonEntity")
    suspend fun getAll(): List<PersonEntity>

    @Query("SELECT * FROM PersonEntity WHERE id LIKE :id LIMIT 1")
    suspend fun get(id: Long): PersonEntity?

    @Insert
    suspend fun insert(personEntity: PersonEntity)

    @Delete
    suspend fun delete(personEntity: PersonEntity)

    @Update
    suspend fun update(personEntity: PersonEntity)
}