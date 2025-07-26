package com.bhavya.foodorder.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.bhavya.foodorder.FoodItemsDataClass.FoodItems

class SharedSearchViewModel : ViewModel() {
    var searchResults: List<FoodItems> = emptyList()
var searchQuery : String=""


}