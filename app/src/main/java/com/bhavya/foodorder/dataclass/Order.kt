package com.bhavya.foodorder.dataclass

data class Order(
    val userName: String,
    val address: String,
    val mobile: String,
    val items: List<Map<String, Any>>,
    val totalAmount: Int,
    val deliveryMethod: String,
    val paymentMode: String,
    val paymentStatus: String,
    val status: String = "Placed",
    val createdAt: Long = System.currentTimeMillis()
)

