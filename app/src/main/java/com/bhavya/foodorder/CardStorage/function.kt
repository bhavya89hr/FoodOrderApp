package com.bhavya.foodorder.util

import com.bhavya.foodorder.FoodItemsDataClass.FoodItems
import com.bhavya.foodorder.model.CartItemEntity

fun FoodItems.toEntity(): CartItemEntity {
    return CartItemEntity(
        itemId = this.id,
        name = this.name,
        price = this.price as Int,
        imageUrl = this.imageUrl
    )
}

fun CartItemEntity.toFoodItem(): FoodItems {
    return FoodItems(
        id = this.itemId,
        name = this.name,
        price = 1,
        imageUrl = this.imageUrl,
        description = "",
        category = "",
        rating = 0.0,
        isAvailable = true
    )
}