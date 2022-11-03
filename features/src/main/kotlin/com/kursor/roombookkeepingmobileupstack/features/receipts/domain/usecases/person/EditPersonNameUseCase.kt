package com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases.person

import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Person
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.PersonId
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.repositories.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EditPersonNameUseCase(
    private val personRepository: PersonRepository
) {

    suspend operator fun invoke(person: Person) = withContext(Dispatchers.IO) {
        personRepository.edit(person)
    }

    suspend operator fun invoke(personId: Long, newName: String) = invoke(
        Person(
            id = PersonId(personId),
            name = newName
        )
    )
}
