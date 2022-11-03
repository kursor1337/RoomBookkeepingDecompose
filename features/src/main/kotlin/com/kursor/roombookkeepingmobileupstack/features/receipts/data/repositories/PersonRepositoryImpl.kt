package com.kursor.roombookkeepingmobileupstack.features.receipts.data.repositories

import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Person
import com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.daos.PersonDao
import com.kursor.roombookkeepingmobileupstack.features.receipts.data.database.entities.PersonEntity
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.PersonId
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.repositories.PersonRepository

class PersonRepositoryImpl(
    val personDao: PersonDao
) : PersonRepository {

    override suspend fun get(id: Long): Person? = personDao.get(id)?.convertToModelEntity()

    override suspend fun getAll(): List<Person> = personDao.getAll().map { it.convertToModelEntity() }

    override suspend fun save(person: Person) = personDao.insert(person.convertToDatabaseEntity())

    override suspend fun edit(person: Person) = personDao.update(person.convertToDatabaseEntity())

    override suspend fun delete(person: Person) = personDao.delete(person.convertToDatabaseEntity())

}

fun PersonEntity.convertToModelEntity(): Person = Person(
    id = PersonId(this.id),
    name = this.name
)

fun Person.convertToDatabaseEntity(): PersonEntity = PersonEntity(
    id = this.id.id,
    name = this.name
)