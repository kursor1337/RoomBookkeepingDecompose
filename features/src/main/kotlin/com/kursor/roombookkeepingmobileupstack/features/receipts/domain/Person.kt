package com.kursor.roombookkeepingmobileupstack.features.receipts.domain

data class Person(
    val id: PersonId,
    val name: String
)

@JvmInline
value class PersonId(val id: Long)