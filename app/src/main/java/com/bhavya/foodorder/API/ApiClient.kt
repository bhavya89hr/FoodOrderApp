package com.bhavya.foodorder.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

object ApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://backend.bitepay.in/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val chatbotApi: ChatbotApi = retrofit.create(ChatbotApi::class.java)
}
