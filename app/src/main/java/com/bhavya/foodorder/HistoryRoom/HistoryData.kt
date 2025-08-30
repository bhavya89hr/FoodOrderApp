package com.bhavya.foodorder.HistoryRoom



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userName: String,
    val address: String,
    val mobileNo: String,
    val foodId: String,
    val foodName: String,
    val price: Int,
    val dateTime: String,
    val photoUrl: String
)
