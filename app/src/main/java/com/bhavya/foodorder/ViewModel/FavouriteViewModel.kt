package com.bhavya.foodorder.ViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.bhavya.foodorder.FoodItemsDataClass.FoodItems

class FavouriteViewModel: ViewModel() {
    private val _cartItems = mutableStateListOf<FoodItems>()
    val cartItems: List<FoodItems> = _cartItems

    fun addToCart(item: FoodItems) {
        _cartItems.add(item)
        Log.d("CartViewModel", "Item added: ${item.name}, Total: ${_cartItems.size}")
    }

    fun removeFromCart(item: FoodItems) {
        _cartItems.remove(item)
    }

    fun clearCart() {
        _cartItems.clear()
    }
}