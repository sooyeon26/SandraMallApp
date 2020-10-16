package com.sooyeon.sandramall.product.category

data class Category(
    val id: Int,
    val name: String
)

val categoryList = listOf(
    Category(0, "Accessory"),
    Category(1, "Leather"),
    Category(2, "Painting"),
    Category(3, "HomeDeco"),
    Category(4, "Others")
)