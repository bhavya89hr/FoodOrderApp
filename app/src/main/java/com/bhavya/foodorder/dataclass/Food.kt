package com.bhavya.foodorder.dataclass

data class MoodRequest(
    val tableId: String,
    val mood: String
)

data class Portion(
    val type: String,
    val price: Double,
    val isAvailable: Boolean
)

data class FoodResponse(
    val name: String,
    val category: String,
    val imageUrl: List<String>,
    val portion: List<Portion>
)
