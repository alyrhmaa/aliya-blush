package com.example.aliya_blush.Data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usulan")
data class UsulanEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val judul: String,

    val deskripsi: String
)