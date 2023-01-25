package com.example.testapp.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardNumber(
    val length: Int?,
    val luhn: Boolean?
)
