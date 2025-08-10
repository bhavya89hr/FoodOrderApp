package com.bhavya.foodorder.favouriteStorage

import androidx.room.*

@Dao
interface FavDao {
    @Query("SELECT * FROM favourite_items")
    suspend fun getAllFavourites(): List<FavouriteItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(item: FavouriteItemEntity)

    @Delete
    suspend fun deleteFavourite(item: FavouriteItemEntity)

    @Query("DELETE FROM favourite_items")
    suspend fun clearFavourites()
}