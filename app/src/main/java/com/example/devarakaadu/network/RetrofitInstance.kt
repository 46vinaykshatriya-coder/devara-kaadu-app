package com.example.devarakaadu.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: OpenRouterApi by lazy {

        Retrofit.Builder()

            .baseUrl("https://openrouter.ai/")

            .addConverterFactory(
                GsonConverterFactory.create()
            )

            .build()

            .create(OpenRouterApi::class.java)
    }
}