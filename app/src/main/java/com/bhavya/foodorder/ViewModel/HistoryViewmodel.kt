package com.bhavya.foodorder.ViewModel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bhavya.foodorder.FoodItemsDataClass.FoodItems
import com.bhavya.foodorder.HistoryRoom.HistoryDatabase
import com.bhavya.foodorder.HistoryRoom.HistoryEntity

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HistoryViewmodel(application: Application) : AndroidViewModel(application) {
    private val dao = HistoryDatabase.getDatabase(application).historyDao()

    // Collect history
    val historyItems = dao.getAllHistory()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun insertHistory(history: HistoryEntity) {
        viewModelScope.launch {
            dao.insertHistory(history)
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            dao.clearHistory()
        }
    }
}
