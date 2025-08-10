package com.bhavya.foodorder.favouriteStorage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavouriteItemEntity::class], version = 2)
abstract class FavouriteDatabase : RoomDatabase() {
    abstract fun favDao(): FavDao
}