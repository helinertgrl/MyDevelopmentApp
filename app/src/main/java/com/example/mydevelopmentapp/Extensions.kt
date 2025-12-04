package com.example.mydevelopmentapp

import androidx.compose.runtime.Composable
import java.nio.channels.Selector
import java.text.NumberFormat
import java.util.Locale


fun Double.toCurrencyString(currencyCode: String = "USD"): String{

    val format = NumberFormat.getCurrencyInstance(Locale.getDefault())

    format.currency = java.util.Currency.getInstance(currencyCode)

    return format.format(this)
}


fun <T, R : Comparable<R>> List<T>.sortByField(selector: (T) -> R): List<T> {
    return this.sortedBy { item -> selector(item) }
}