package com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases.person


import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Person
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.repositories.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeletePersonUseCase(
    private val personRepository: PersonRepository
) {

    suspend operator fun invoke(person: Person) = withContext(Dispatchers.IO) {
        personRepository.delete(person)
    }

}