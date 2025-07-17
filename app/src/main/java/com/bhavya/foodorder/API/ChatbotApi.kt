package com.bhavya.foodorder.API

import com.bhavya.foodorder.dataclass.FoodResponse
import com.bhavya.foodorder.dataclass.MoodRequest

import retrofit2.http.Body
import retrofit2.http.POST

interface ChatbotApi {
    @POST("recommendation/byMood")
    suspend fun getMoodBasedFood(@Body request: MoodRequest): List<FoodResponse>
}
