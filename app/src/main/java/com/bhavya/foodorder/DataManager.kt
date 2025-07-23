package com.bhavya.foodorder

import android.content.Context
import com.bhavya.foodorder.FoodItemsDataClass.FoodItems
import com.google.gson.Gson

object DataManager {
var data=emptyArray<FoodItems>()
    fun AccessData(context:Context){
val inputStream=context.assets.open("recip.json")
        val size:Int=inputStream.available()
        val buffer= ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json= String(buffer, Charsets.UTF_8)
        val gson= Gson()
        data =gson.fromJson(json, Array<FoodItems>::class.java)

    }
}