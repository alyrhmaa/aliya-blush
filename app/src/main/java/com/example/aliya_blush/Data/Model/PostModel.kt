package com.example.aliya_blush.Data.Model

import com.google.gson.annotations.SerializedName

data class PostModel(
    val id: Int,
    val title: String,
    
    // Memberitahu GSON untuk mengambil dari "body" atau "description"
    @SerializedName(value = "body", alternate = ["description"])
    val body: String?,
    
    // Memberitahu GSON untuk mengambil dari "image" atau "thumbnail"
    @SerializedName(value = "image", alternate = ["thumbnail"])
    val image: String?
)
