package com.syzible.search.data

import com.syzible.search.type.DrinkCategory

data class SearchResult(
    val name: String,
    val abv: Double,
    val category: DrinkCategory,
    val styles: ArrayList<String>
)
