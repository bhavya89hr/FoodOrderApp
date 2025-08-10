package com.bhavya.foodorder.profileRoom

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bhavya.foodorder.favouriteStorage.FavouriteItemEntity

@Database(entities = [ProfileEntity::class], version = 1)

abstract class ProfileData: RoomDatabase(){
    abstract fun ProfileDao(): ProfileDao
}