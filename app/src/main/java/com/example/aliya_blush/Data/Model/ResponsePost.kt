package com.example.aliya_blush.Data.Model

import com.google.gson.annotations.SerializedName

data class ResponsePost(
    // alternate memungkinkan model ini menerima kunci "posts" ATAU "products" dari API
    @SerializedName(value = "posts", alternate = ["products"])
    val posts: List<PostModel>
)
