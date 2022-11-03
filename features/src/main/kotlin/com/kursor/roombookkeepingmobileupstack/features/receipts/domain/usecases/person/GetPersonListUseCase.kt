package com.kursor.roombookkeepingmobileupstack.features.receipts.domain.usecases.person

import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.repositories.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPersonListUseCase(
    private val personRepository: PersonRepository
) {

    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        personRepository.getAll()
    }

}