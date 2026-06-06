package com.example.aliya_blush.Data.Api

import com.example.aliya_blush.Data.Model.ResponsePost
import retrofit2.http.GET

interface PostApiService {

    @GET("posts")
    suspend fun getPosts(): ResponsePost

    // Menambahkan endpoint products untuk mendapatkan data yang memiliki gambar
    @GET("products")
    suspend fun getPhotos(): ResponsePost
}
