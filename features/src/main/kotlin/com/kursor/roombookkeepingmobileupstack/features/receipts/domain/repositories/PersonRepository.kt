package com.kursor.roombookkeepingmobileupstack.features.receipts.domain.repositories

import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Person

interface PersonRepository {

    suspend fun get(id: Long): Person?

    suspend fun getAll(): List<Person>

    suspend fun save(person: Person)

    suspend fun edit(person: Person)

    suspend fun delete(person: Person)

}