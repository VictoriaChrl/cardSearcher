package com.example.testapp.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Bank(
    val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)
