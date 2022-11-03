package com.kursor.roombookkeepingmobileupstack.features.receipts.domain

import java.util.*

data class Receipt(
    val id: Long,
    val name: String,
    val dateTime: Date,
    val priceList: List<Price>,
) {

    val persons: List<Person>
        get() = priceList.flatMap { it.persons }.toSet().toList()
}