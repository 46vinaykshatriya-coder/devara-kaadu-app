package com.example.devarakaadu.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private const val BASE_URL =
        "https://openrouter.ai/api/"

    private val logging =

        HttpLoggingInterceptor().apply {

            level =
                HttpLoggingInterceptor.Level.BODY
        }

    private val client =

        OkHttpClient.Builder()

            .connectTimeout(
                60,
                TimeUnit.SECONDS
            )

            .readTimeout(
                60,
                TimeUnit.SECONDS
            )

            .writeTimeout(
                60,
                TimeUnit.SECONDS
            )

            .addInterceptor(logging)

            .build()

    val api: OpenRouterApi by lazy {

        Retrofit.Builder()

            .baseUrl(BASE_URL)

            .client(client)

            .addConverterFactory(
                GsonConverterFactory.create()
            )

            .build()

            .create(OpenRouterApi::class.java)
    }
}