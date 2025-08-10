package com.bhavya.foodorder.ViewModel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.bhavya.foodorder.FoodItemsDataClass.FoodItems
import com.bhavya.foodorder.favouriteStorage.FavouriteDatabase
import com.bhavya.foodorder.favouriteStorage.FavouriteItemEntity
import com.bhavya.foodorder.favouriteStorage.toEntity
import com.bhavya.foodorder.favouriteStorage.toFoodItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        FavouriteDatabase::class.java,
        "favourite_db"
    ).fallbackToDestructiveMigration()
        .build()

    private val favDao = db.favDao()

    private val _favouriteItems = mutableStateListOf<FoodItems>()
    val favouriteItems: List<FoodItems> = _favouriteItems

    init {
        loadFavouriteItems()
    }

    private fun loadFavouriteItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val favEntities = favDao.getAllFavourites()
            _favouriteItems.addAll(favEntities.map { it.toFoodItem() })
        }
    }

    fun addToFavourites(item: FoodItems) {
        _favouriteItems.add(item)
        viewModelScope.launch(Dispatchers.IO) {
            favDao.insertFavourite(item.toEntity())
        }
    }

    fun removeFromFavourites(item: FoodItems) {
        _favouriteItems.remove(item)
        viewModelScope.launch(Dispatchers.IO) {
            favDao.deleteFavourite(item.toEntity())
        }
    }

    fun clearFavourites() {
        _favouriteItems.clear()
        viewModelScope.launch(Dispatchers.IO) {
            favDao.clearFavourites()
        }
    }
}
