package net.sherafatpour.daggertest.data.models

data class CurrencyModel(
    val base: String,
    val date: String,
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int
)