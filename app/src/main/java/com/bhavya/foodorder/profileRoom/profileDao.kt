package com.bhavya.foodorder.profileRoom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bhavya.foodorder.favouriteStorage.FavouriteItemEntity

@Dao
interface ProfileDao{
    @Query("SELECT * FROM profileitems WHERE id = 1 LIMIT 1")
    suspend fun getallItems(): ProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(profile: ProfileEntity)

}