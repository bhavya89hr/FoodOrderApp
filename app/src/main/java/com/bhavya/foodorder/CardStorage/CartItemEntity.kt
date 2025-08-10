package com.bhavya.foodorder.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey val itemId: String,
    val name: String,
    val price: Int,
    val imageUrl: String
)
