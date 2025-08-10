package com.bhavya.foodorder.favouriteStorage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_items")
data class FavouriteItemEntity(
    @PrimaryKey val itemId: String  ,
    val name: String,
    val price: Int,
    val imageUrl: String
)