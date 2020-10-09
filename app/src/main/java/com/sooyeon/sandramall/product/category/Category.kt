package com.sooyeon.sandramall.product.category

data class Category(
    val id: Int,
    val name: String
)

val categoryList = listOf(
    Category(0, "Accessory"),
    Category(1, "Food"),
    Category(2, "Household"),
    Category(3, "Leather"),
    Category(4, "Painting")
)