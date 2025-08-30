package com.bhavya.foodorder.favouriteStorage


import com.bhavya.foodorder.FoodItemsDataClass.FoodItems

fun FoodItems.toEntity(): FavouriteItemEntity {
    return FavouriteItemEntity(
        itemId = this.id,
        name = this.name,
        price = this.price as Int,
        imageUrl = this.imageUrl
    )
}

fun FavouriteItemEntity.toFoodItem(): FoodItems {
    return FoodItems(
        id = this.itemId,
        name = this.name,
        price = this.price,
        imageUrl = this.imageUrl,
        description = "",
        category = "",
        rating = 0.0,
        isAvailable = true
    )
}