package com.example.mydevelopmentapp.util

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale


fun Double.toCurrencyString(currencyCode: String = "USD"): String{

    val format = NumberFormat.getCurrencyInstance(Locale.getDefault())

    format.currency = Currency.getInstance(currencyCode)

    return format.format(this)
}


fun <T, R : Comparable<R>> List<T>.sortByField(selector: (T) -> R): List<T> {
    return this.sortedBy { item -> selector(item) }
}