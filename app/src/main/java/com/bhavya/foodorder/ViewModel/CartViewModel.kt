package com.bhavya.foodorder.ViewModel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.bhavya.foodorder.FoodItemsDataClass.FoodItems
import com.bhavya.foodorder.model.AppDatabase
import com.bhavya.foodorder.util.toEntity
import com.bhavya.foodorder.util.toFoodItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "cart_db"
    ).fallbackToDestructiveMigration().build()

    private val cartDao = db.cartDao()
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val _cartItems = mutableStateListOf<FoodItems>()
    val cartItems: List<FoodItems> = _cartItems

    init {
        loadCartItems()
    }

    private fun loadCartItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val entities = cartDao.getAllItems()
            val items = entities.map { it.toFoodItem() }
            _cartItems.addAll(items)
        }
    }

    fun addToCart(item: FoodItems) {
        _cartItems.add(item)
        viewModelScope.launch(Dispatchers.IO) {
            cartDao.insertItem(item.toEntity())

            val uid = auth.currentUser?.uid ?: return@launch
            firestore.collection("carts")
                .document(uid)
                .collection("items")
                .document(item.id.toString())
                .set(item)
        }
    }

    fun removeFromCart(item: FoodItems) {
        _cartItems.remove(item)
        viewModelScope.launch(Dispatchers.IO) {
            cartDao.deleteItem(item.toEntity())

            val uid = auth.currentUser?.uid ?: return@launch
            firestore.collection("carts")
                .document(uid)
                .collection("items")
                .document(item.id.toString())
                .delete()
        }
    }

    fun clearCart() {
        _cartItems.clear()
        viewModelScope.launch(Dispatchers.IO) {
            cartDao.clearCart()

            // 🔹 Clear from Firestore
            val uid = auth.currentUser?.uid ?: return@launch
            firestore.collection("carts")
                .document(uid)
                .collection("items")
                .get()
                .addOnSuccessListener { snapshot ->
                    snapshot.documents.forEach { it.reference.delete() }
                }
        }
    }
}
