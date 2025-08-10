package com.bhavya.foodorder.model

import androidx.room.*

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items")
    suspend fun getAllItems(): List<CartItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: CartItemEntity)

    @Delete
    suspend fun deleteItem(item: CartItemEntity)

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()
}