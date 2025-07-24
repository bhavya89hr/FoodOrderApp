package com.bhavya.foodorder.FoodItemsDataClass

data class FoodItems (
    val id: String,
    val name: String,
    val price: Int,
    val imageUrl: String,
    val description: String,
    val category: String,
    val rating: Double,
    val isAvailable: Boolean)