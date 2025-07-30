package com.bhavya.foodorder.API

data class kvhvItem(
    val category: String,
    val description: String,
    val id: String,
    val imageUrl: String,
    val isAvailable: Boolean,
    val name: String,
    val price: Int,
    val rating: Double
)