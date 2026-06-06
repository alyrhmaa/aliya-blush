package com.example.aliya_blush.Data.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostApiClient {

    private const val BASE_URL =
        "https://dummyjson.com/"

    val apiService: PostApiService by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(PostApiService::class.java)
    }
}