package com.bhavya.foodorder.profileRoom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profileitems")
data class ProfileEntity(
    @PrimaryKey val id: Int = 1,
    val Name: String,
    val Address: String,
    val Email:String,
    val MObileNO: String
)
