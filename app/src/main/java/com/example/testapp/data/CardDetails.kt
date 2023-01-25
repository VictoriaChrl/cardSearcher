package com.example.testapp.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardDetails(
val number: CardNumber,
val scheme: String?,
val type: String?,
val brand: String?,
val prepaid: Boolean?,
val country: Country,
val bank: Bank
)
