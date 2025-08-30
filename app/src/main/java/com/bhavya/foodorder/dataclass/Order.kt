package com.bhavya.foodorder.dataclass



data class Order(
    val userId: String = "",
    val name: String = "",
    val items: List<Map<String, Any>> = emptyList(),
    val total: Double = 0.0,
    val paymentMethod: String = "",
    val deliveryMethod: String = "",
    val timestamp: Long = System.currentTimeMillis()
)

