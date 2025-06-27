package com.bhavya.foodorder.API

import com.bhavya.foodorder.dataclass.FoodItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FoodApi {

    @GET("api/food")
    suspend fun getFoodItems(): List<FoodItem>

    @Multipart
    @POST("api/food")
    suspend fun addFoodItem(
        @Part("name") name: RequestBody,
        @Part("price") price: RequestBody,
        @Part photo: MultipartBody.Part
    ): FoodItem
}