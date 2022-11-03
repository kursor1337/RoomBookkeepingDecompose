package com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases.person

import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.repositories.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPersonUseCase(
    private val personRepository: PersonRepository
) {

    suspend operator fun invoke(id: Long) = withContext(Dispatchers.IO) {
        personRepository.get(id)
    }

}